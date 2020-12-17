package com.example.cryptoapp.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cryptoapp.R
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.utils.convertTimeStampToTime
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageOptions = RequestOptions()
        .placeholder(R.drawable.ic_baseline_attach_money_24)
        .fallback(R.drawable.ic_baseline_attach_money_24)


    fun bind(coinPriceInfo: CoinPriceInfo) {
        "${coinPriceInfo.fromSymbol} / ${coinPriceInfo.toSymbol}".also {
            itemView.title_coin.text = it
        }
        itemView.price_coin.text = coinPriceInfo.price
        itemView.last_update.text = convertTimeStampToTime(timeStamp = coinPriceInfo.lastUpdate)
        coinPriceInfo.imageUrl?.let { downloadImage(uri= it) }
    }

    private fun downloadImage(uri: String) {
        Glide.with(itemView.context)
            .load(uri)
            .apply(imageOptions)
            .into(itemView.logo_coin)
    }
}