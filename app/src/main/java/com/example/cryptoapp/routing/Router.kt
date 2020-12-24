package com.example.cryptoapp.routing

import com.example.cryptoapp.data.pojo.CoinPriceInfo

interface Router {
    fun openCoinListFragment(count : Int)
    fun openDetailCoin(coinPriceInfo: CoinPriceInfo)
}