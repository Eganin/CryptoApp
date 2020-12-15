package com.example.cryptoapp.api

import com.example.cryptoapp.data.pojo.CoinPriceInfoRawData
import com.example.cryptoapp.data.pojo.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET(value = "top/totalvolfull")
    @Headers(value = ["Content-Type: application/json"])
    fun getTopCoinInto(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = DEFAULT_API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = DEFAULT_LIMIT,
        @Query(QUERY_PARAM_TO_SYMBOL) toSymbol: String = DEFAULT_SYMBOL
    ): Single<Response>

    @GET(value = "pricemultifull")
    @Headers(value = ["Content-Type: application/json"])
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = DEFAULT_API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOL) fromSymbol: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) toSymbol: String = DEFAULT_SYMBOL
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_FROM_SYMBOL = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"

        private const val DEFAULT_API_KEY =
            "754fccc5af75b5b0da2e4de7f80f3929f6f2cc6ff263bd08a18535c6d9087829"
        private const val DEFAULT_LIMIT = 10
        private const val DEFAULT_SYMBOL = "USD"
    }
}