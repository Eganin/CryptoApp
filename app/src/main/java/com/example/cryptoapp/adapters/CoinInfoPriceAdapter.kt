package com.example.cryptoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.data.pojo.CoinPriceInfo
import com.example.cryptoapp.views.CoinViewHolder


class CoinInfoPriceAdapter : RecyclerView.Adapter<CoinViewHolder>() {

    private var coinInfoPriceList : List<CoinPriceInfo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CoinViewHolder(
        itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coin_info, parent, false
        )
    )

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) =
        holder.bind(coinPriceInfo = coinInfoPriceList[position])

    override fun getItemCount() = coinInfoPriceList.size

    fun bindCoins(data: List<CoinPriceInfo>) {
        coinInfoPriceList = data
        notifyDataSetChanged()
    }

}