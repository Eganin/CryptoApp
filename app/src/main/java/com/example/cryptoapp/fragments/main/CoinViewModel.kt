package com.example.cryptoapp.fragments.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.api.ApiService
import com.example.cryptoapp.database.AppDatabase
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

    fun loadData(api : ApiService?){
        api?.let {
            compositeDisposable.add(
                it.getTopCoinInto()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("API", it.toString())
                    },{
                        //errors.value=it
                    })
            )
        }
    }
}