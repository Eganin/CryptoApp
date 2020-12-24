package com.example.cryptoapp.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.application.CryptoApp
import com.example.cryptoapp.database.AppDatabase
import com.example.cryptoapp.repositories.CoinRepository
import io.reactivex.disposables.CompositeDisposable


class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(context = application)

    val priceList = database.coinPriceInfoDao().getPriceList()

    private val compositeDisposable = CompositeDisposable()
    private val api = (application as CryptoApp).apiService

    private val _errors = MutableLiveData<Throwable>()
    val errors: LiveData<Throwable> = _errors

    private val _state = MutableLiveData<State>()
    val states: LiveData<State> = _state

    fun startDownloading(limit: Int) {
        _state.value = State.LOADING

        CoinRepository(
            apiService = api,
            database = database,
            errorsData = _errors,
            limit = limit
        ).getData()

        _state.value = State.SUCCESS
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun getDetailInfo(fromSymbol: String) =
        database.coinPriceInfoDao().getPriceInfoAboutCoin(fsym = fromSymbol)

}

enum class State { DEFAULT, LOADING, SUCCESS }