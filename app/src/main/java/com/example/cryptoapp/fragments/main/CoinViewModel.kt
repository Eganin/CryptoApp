package com.example.cryptoapp.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.api.ApiService
import com.example.cryptoapp.application.CryptoApp
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.data.pojo.CoinPriceInfoRawData
import com.example.cryptoapp.database.AppDatabase
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(context = application)

    val priceList= database.coinPriceInfoDao().getPriceList()

    private val compositeDisposable = CompositeDisposable()

    private val _errors = MutableLiveData<Throwable>()
    val errors: LiveData<Throwable> = _errors

    private val _state = MutableLiveData<State>()
    val states: LiveData<State> = _state

    init {
        _state.value = State.LOADING
        loadData(api = (application as CryptoApp).apiService)
        _state.value = State.SUCCESS
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getDetailInfo(fromSymbol: String) =
        database.coinPriceInfoDao().getPriceInfoAboutCoin(fsym = fromSymbol)

    private fun loadData(api: ApiService) {
        compositeDisposable.add(
            api.getTopCoinInto()
                //с первой загрузкой применяем map
                .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
                // применение действия над каждым значением map
                .flatMap { api.getFullPriceList(fromSymbol = it) }
                .map { getPriceListFromRawData(coinPriceInfoRawData = it) }
                // бесконечно повторяем загрузку
                .repeat()
                // выполнить загрузку заново если предыдущая упадет
                .retry()
                // repeat повторяется через какое-то время
                .delaySubscription(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    database.coinPriceInfoDao().insertPriceList(list = it!!)
                }, {
                   _errors.postValue(it)
                })
        )
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonData ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }
}

enum class State { DEFAULT, LOADING, SUCCESS }