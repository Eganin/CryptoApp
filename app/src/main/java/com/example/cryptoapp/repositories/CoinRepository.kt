package com.example.cryptoapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.api.ApiService
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.data.pojo.CoinPriceInfoRawData
import com.example.cryptoapp.database.AppDatabase
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinRepository(
    val apiService: ApiService,
    val database: AppDatabase,
    val errorsData: MutableLiveData<Throwable>,
    val limit: Int
) {

    private val compositeDisposable = CompositeDisposable()

    fun getData() {
        compositeDisposable.add(
            apiService.getTopCoinInto(limit = limit)
                .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
                .flatMap { apiService.getFullPriceList(fromSymbol = it) }
                .map { getPriceListFromRawData(coinPriceInfoRawData = it) }
                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    database.coinPriceInfoDao().deletePriceList()
                    database.coinPriceInfoDao().insertPriceList(list = it!!)
                }, {
                    errorsData.postValue(it)
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