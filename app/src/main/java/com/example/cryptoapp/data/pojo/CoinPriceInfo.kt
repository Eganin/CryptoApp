package com.example.cryptoapp.data.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
    var type: String? = null,

    @SerializedName("MARKET")
    @Expose
    var market: String? = null,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("FROMSYMBOL")
    @Expose
    var fromSymbol: String? = null,

    @SerializedName("TOSYMBOL")
    @Expose
    var toSymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    var flags: String? = null,

    @SerializedName("PRICE")
    @Expose
    var price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    var lastUpdate: Int? = null,

    @SerializedName("MEDIAN")
    @Expose
    var median: Double? = null,

    @SerializedName("LASTVOLUME")
    @Expose
    var lastVolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    @Expose
    var lastVolumeTo: Double? = null,

    @SerializedName("LASTTRADEID")
    @Expose
    var lastRadeId: String? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    var volumeDay: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    var volumeDayTo: Double? = null,

    @SerializedName("VOLUME24HOUR")
    @Expose
    var volume24Hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    var volume24HourTo: Double? = null,

    @SerializedName("OPENDAY")
    @Expose
    var openday: Double? = null,

    @SerializedName("HIGHDAY")
    @Expose
    var highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    var lowday: Double? = null,

    @SerializedName("OPEN24HOUR")
    @Expose
    var open24hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    @Expose
    var high24hour: Double? = null,

    @SerializedName("LOW24HOUR")
    @Expose
    var low24hour: Double? = null,

    @SerializedName("LASTMARKET")
    @Expose
    var lastMarket: String? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    var volumeHour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    var volumeHourTo : Double? = null,

    @SerializedName("OPENHOUR")
    @Expose
    var openHour: Double? = null,

    @SerializedName("HIGHHOUR")
    @Expose
    var highHour: Double? = null,

    @SerializedName("LOWHOUR")
    @Expose
    var lowHour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    var topTierVolume24Hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    var topTierVolume24HourTo: Double? = null,

    @SerializedName("CHANGE24HOUR")
    @Expose
    var change24hour: Double? = null,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    var changePCT24Hour: Double? = null,

    @SerializedName("CHANGEDAY")
    @Expose
    var changeDay: Double? = null,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    var changePctDay: Double? = null,

    @SerializedName("CHANGEHOUR")
    @Expose
    var changeHour: Double? = null,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    var changePctHour: Double? = null,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    var conversionType: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    var conversionSymbol: String? = null,

    @SerializedName("SUPPLY")
    @Expose
    var supply: Int? = null,


    @SerializedName("TOTALVOLUME24H")
    @Expose
    var totalVolume24H: Double? = null,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    var totalVolume24Hto: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    var totalTopTierVolume24H: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    var totalTopTierVolume24Hto: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    var imageUrl: String? = null
)