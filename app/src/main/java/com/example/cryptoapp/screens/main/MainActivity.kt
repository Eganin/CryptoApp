package com.example.cryptoapp.screens.main

import android.os.Bundle
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoPriceAdapter
import com.example.cryptoapp.common.SampleBottomSheet
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.exceptions.ExceptionNavigationView
import com.example.cryptoapp.fragments.detail.DetailCoinFragment
import com.example.cryptoapp.fragments.main.CoinListFragment
import com.example.cryptoapp.routing.Router
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Router, CoinInfoPriceAdapter.OnCLickCoinListener,
    SampleBottomSheet.ListenerBottom {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            handlerNavigationView()
        } catch (e: ExceptionNavigationView) {
            e.printStackTrace()
        }
        if (savedInstanceState == null) {
            openCoinListFragment(count = 10)
        }

    }


    override fun onCLick(coinPriceInfo: CoinPriceInfo) {
        openDetailCoin(coinPriceInfo = coinPriceInfo)
    }

    override fun openCoinListFragment(count: Int) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_container, CoinListFragment.newInstance(count = count))
            addToBackStack(null)
            commit()
        }
    }

    override fun openDetailCoin(coinPriceInfo: CoinPriceInfo) {
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
                R.id.count_coins -> {
                    SampleBottomSheet().show(supportFragmentManager, "main_dialog")
                    true
                }
                else -> throw ExceptionNavigationView()
            }
        }
    }

    override fun positiveClick(count: Int) {
        val lastFragment = supportFragmentManager.fragments.last()
        supportFragmentManager.beginTransaction().apply {
            remove(lastFragment)
            commit()
        }
        openCoinListFragment(count = count)
    }

}