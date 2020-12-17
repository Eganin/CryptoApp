package com.example.cryptoapp.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoPriceAdapter
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.utils.imageOptions
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinViewHolder(itemView: View , var listener : CoinInfoPriceAdapter.OnCLickCoinListener?) : RecyclerView.ViewHolder(itemView) {

    fun bind(coinPriceInfo: CoinPriceInfo) {
        itemView.setOnClickListener{
            listener?.onCLick(coinPriceInfo=coinPriceInfo)
        }

        with(coinPriceInfo) {
            with(itemView) {
                "$fromSymbol / $toSymbol".also { title_coin.text = it }

                price_coin.text = price

                (context.getString(R.string.text_last_update_title) + getFormattedTime()).also {
                    last_update.text = it
                }

                downloadImage(uri = getFullImageUrl())
            }
        }
    }

    private fun downloadImage(uri: String) {
        Glide.with(itemView.context)
            .load(uri)
            .apply(imageOptions)
            .into(itemView.logo_coin)
    }
}