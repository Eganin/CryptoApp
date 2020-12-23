package com.example.cryptoapp.fragments.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoPriceAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list_coins.*

class CoinListFragment : Fragment() {

    private lateinit var coinViewModel: CoinViewModel
    private val adapter = CoinInfoPriceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_coins, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        coinViewModel = ViewModelProviders.of(this@CoinListFragment)[CoinViewModel::class.java]

        coinViewModel.priceList.observe(this@CoinListFragment, Observer {
            adapter.bindCoins(data = it)
        })

        coinViewModel.errors.observe(this@CoinListFragment, Observer { showError(t = it) })

        coinViewModel.states.observe(this@CoinListFragment, Observer { setLoader(state = it) })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CoinInfoPriceAdapter.OnCLickCoinListener) {
            adapter.onCLickCoinListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        adapter.onCLickCoinListener = null
    }

    private fun setupUI() {
        coins_recycler_view.adapter = adapter
        coins_recycler_view.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun showError(t: Throwable) {
        Snackbar.make(
            main_coordinator_layout,
            getString(R.string.error_message_text) + t.message.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setLoader(state: State) {
        when (state) {
            State.LOADING -> {main_progress_bar.isVisible = true;Log.d("AAA","loading")}
            State.SUCCESS -> main_progress_bar.isVisible = false
            State.DEFAULT -> main_progress_bar.visibility = View.GONE
        }
    }
}