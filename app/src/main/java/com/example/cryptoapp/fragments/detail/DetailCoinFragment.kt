package com.example.cryptoapp.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.R
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.fragments.main.CoinViewModel
import kotlinx.android.synthetic.main.fragment_detail_coin.*

class DetailCoinFragment private constructor() : Fragment() {

    private lateinit var coinViewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail_coin, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinViewModel = ViewModelProviders.of(this@DetailCoinFragment)[CoinViewModel::class.java]
        coinViewModel.getDetailInfo(arguments?.getString(SAVE_FROM_SYMBOL) ?: "")
            .observe(this@DetailCoinFragment, Observer {
                bindData(data = it)
            })
    }

    private fun bindData(data: CoinPriceInfo) {
        from_symbol_text.text = data.fromSymbol
        to_symbol_text.text = data.toSymbol
        price_value.text = data.price
        price_min_value.text = data
        price_max_value.text = data
        update_value.text = data.getFormattedTime()
    }

    companion object {
        fun newInstance(coinPriceInfo: CoinPriceInfo): DetailCoinFragment {
            val fragment = DetailCoinFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            bundle.getString(SAVE_FROM_SYMBOL, coinPriceInfo.fromSymbol)
            return fragment
        }

        private const val SAVE_FROM_SYMBOL = "SAVE_FROM_SYMBOL"
    }
}