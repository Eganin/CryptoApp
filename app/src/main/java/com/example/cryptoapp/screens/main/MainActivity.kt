package com.example.cryptoapp.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoPriceAdapter
import com.example.cryptoapp.application.CryptoApp
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.exceptions.ExceptionNavigationView
import com.example.cryptoapp.fragments.detail.DetailCoinFragment
import com.example.cryptoapp.fragments.main.CoinListFragment
import com.example.cryptoapp.fragments.main.CoinViewModel
import com.example.cryptoapp.fragments.select.count.reviews.SelectCountReviews
import com.example.cryptoapp.views.CoinViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoinInfoPriceAdapter.OnCLickCoinListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{
            handlerNavigationView()
        }catch (e : ExceptionNavigationView){
            e.printStackTrace()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_container, CoinListFragment())
                addToBackStack(null)
                commit()
            }
        }

    }

    override fun onCLick(coinPriceInfo: CoinPriceInfo) {
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.main_container,
                DetailCoinFragment.newInstance(coinPriceInfo = coinPriceInfo)
            )
            addToBackStack(null)
            commit()
        }
    }

    private fun handlerNavigationView() {
        navigation_view_main.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.count_coins -> TODO()
                else -> throw ExceptionNavigationView()
            }
        }
    }
}