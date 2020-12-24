package com.example.cryptoapp.fragments.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.fragments.main.CoinViewModel
import com.example.cryptoapp.utils.imageOptions
import kotlinx.android.synthetic.main.fragment_detail_coin.*
import kotlinx.android.synthetic.main.item_coin_info.view.*

class DetailCoinFragment : Fragment() {

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
        price_min_value.text = data.lowDay
        price_max_value.text = data.highDay
        last_deal_value.text= data.lastMarket
        update_value.text = data.getFormattedTime()
        downloadImage(uri = data.getFullImageUrl())
    }

    private fun downloadImage(uri: String) {
        Glide.with(requireContext())
            .load(uri)
            .apply(imageOptions)
            .into(coin_poster)
    }

    companion object {
        fun newInstance(coinPriceInfo: CoinPriceInfo): DetailCoinFragment {
            val fragment = DetailCoinFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            bundle.putString(SAVE_FROM_SYMBOL, coinPriceInfo.fromSymbol)
            return fragment
        }

        private const val SAVE_FROM_SYMBOL = "SAVE_FROM_SYMBOL"
    }
}