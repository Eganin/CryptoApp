package com.example.cryptoapp.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.example.cryptoapp.application.CryptoApp

class CoinListFragment : Fragment() {

    private lateinit var coinViewModel : CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =inflater.inflate(R.layout.fragment_list_coins, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinViewModel = ViewModelProviders.of(this@CoinListFragment)[CoinViewModel::class.java]
        //coinViewModel.priceList.observe(this@CoinListFragment , Observer{
        //    println(it.toString())
        //})
        coinViewModel.getDetailInfo(fromSymbol = "BTC").observe(this, Observer{
           Log.d("AAA",it.toString())
        })
    }
}