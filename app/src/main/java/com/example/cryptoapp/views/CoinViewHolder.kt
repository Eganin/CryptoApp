package com.example.cryptoapp.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cryptoapp.R
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface OnCLickCoinListener{
        fun onCLick(coinPriceInfo : CoinPriceInfo)
    }

    var onCLickCoinListener : OnCLickCoinListener? = null

    private val imageOptions = RequestOptions()
        .placeholder(R.drawable.ic_baseline_attach_money_24)
        .fallback(R.drawable.ic_baseline_attach_money_24)
        .circleCrop()


    fun bind(coinPriceInfo: CoinPriceInfo) {
        itemView.setOnClickListener{
            onCLickCoinListener?.onCLick(coinPriceInfo=coinPriceInfo)
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