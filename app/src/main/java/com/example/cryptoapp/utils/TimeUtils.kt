package com.example.cryptoapp.utils

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