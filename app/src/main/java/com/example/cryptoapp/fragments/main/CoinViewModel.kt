package com.example.cryptoapp.fragments.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.api.ApiService
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.data.pojo.CoinPriceInfoRawData
import com.example.cryptoapp.database.AppDatabase
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(context = application)
    private var coinsList = database.coinPriceInfoDao()
    private val compositeDisposable = CompositeDisposable()
    val errors = MutableLiveData<Throwable>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun loadData(api: ApiService?) {
        api?.let {
            compositeDisposable.add(
                it.getTopCoinInto()
                    //с первой загрузкой применяем map
                    .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
                    // применение действия над каждым значением map
                    .flatMap { api.getFullPriceList(fromSymbol = it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("CoinViewModel", it.toString())
                    }, {
                        errors.value = it
                    })
            )
        }
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val json = coinPriceInfoRawData.coinPriceInfoJsonData
        if (json == null) return result
        val coinKeySet = json.keySet()
        for (i in coinKeySet) {
            val currency = json.getAsJsonObject(i)
            val currencyKeySet = currency.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo =
                    Gson().fromJson(json.getAsJsonObject(currencyKey), CoinPriceInfo::class.java)
                result.add(element = priceInfo)
            }
        }
        return result
    }
}