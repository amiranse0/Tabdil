package com.example.tabdil.data.model.remote

import com.google.gson.annotations.SerializedName as SN

data class Currency(
    @SN("change_percent")
    val changePercent: String,

    @SN("id")
    val id: Int,

    @SN("name")
    val name: String,

    @SN("name_fa")
    val persianName: String,

    @SN("price_in_usdt")
    val priceInUsdt: String,

    @SN("symbol")
    val symbol: String,

    @SN("usdt_volume")
    val usdtVolume: String,

    @SN("volume")
    val volume: String
)