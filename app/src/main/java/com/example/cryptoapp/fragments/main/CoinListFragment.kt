package com.example.cryptoapp.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoPriceAdapter
import com.example.cryptoapp.application.CryptoApp
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.fragment_list_coins.*

class CoinListFragment : Fragment() {

    private lateinit var coinViewModel: CoinViewModel
    private lateinit var adapter: CoinInfoPriceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_coins, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_progress_bar.visibility = View.VISIBLE
        setupUI()
        coinViewModel = ViewModelProviders.of(this@CoinListFragment)[CoinViewModel::class.java]

        coinViewModel.priceList.observe(this@CoinListFragment, Observer {
            adapter.bindCoins(data = it)
            main_progress_bar.visibility = View.GONE
        })
    }

    private fun setupUI() {
        adapter = CoinInfoPriceAdapter()
        coins_recycler_view.adapter = adapter
        coins_recycler_view.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}