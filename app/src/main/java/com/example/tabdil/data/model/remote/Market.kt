package com.example.tabdil.data.model.remote

import com.google.gson.annotations.SerializedName as SN

data class Market(
    @SN("change_percent")
    val changePercent: String,

    @SN("id")
    val id: Int,

    @SN("last_trade_price")
    val lastTradePrice: String,

    @SN("name_fa")
    val persianName: String,

    @SN("price")
    val price: String,

    @SN("price_precision")
    val pricePrecision: Int,

    @SN("symbol")
    val symbol: String,

    @SN("first_currency")
    val firstCurrency: PariCurrency,

    @SN("second_currency")
    val secondCurrency: PariCurrency

)