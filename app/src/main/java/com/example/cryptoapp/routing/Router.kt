package com.example.cryptoapp.routing

import com.example.cryptoapp.data.pojo.CoinPriceInfo

interface Router {
    fun openCoinListFragment()
    fun openDetailCoin(coinPriceInfo: CoinPriceInfo)
}