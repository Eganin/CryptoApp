package com.example.cryptoapp.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.example.cryptoapp.application.CryptoApp
import com.example.cryptoapp.fragments.main.CoinListFragment
import com.example.cryptoapp.fragments.main.CoinViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_container, CoinListFragment())
            commit()
        }

    }
}