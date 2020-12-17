package com.example.cryptoapp.utils

import com.bumptech.glide.request.RequestOptions
import com.example.cryptoapp.R
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimeStampToTime(timeStamp: Long?): String {
    timeStamp ?: return ""
    val stamp = Timestamp(timeStamp * 1000)
    val date = Date(stamp.time)
    val patternDate = "HH:mm:ss"
    val sdf = SimpleDateFormat(patternDate, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

val imageOptions = RequestOptions()
    .placeholder(R.drawable.ic_baseline_attach_money_24)
    .fallback(R.drawable.ic_baseline_attach_money_24)
    .circleCrop()