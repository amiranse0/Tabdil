package com.example.tabdil.data.model

import com.google.gson.annotations.SerializedName as SN

data class Market(
    @SN("change_percent")
    val changePercent: String,

    @SN("high")
    val high: String,

    @SN("id")
    val id: Int,

    @SN("last_trade_price")
    val lastTradePrice: String,

    @SN("low")
    val low: String,

    @SN("name_fa")
    val persianName: String,

    @SN("price")
    val price: String,

    @SN("price_precision")
    val pricePrecision: Int,

    @SN("symbol")
    val symbol: String,

    @SN("usdt_volume")
    val usdtVolume: String,

    @SN("volume")
    val volume: String
)