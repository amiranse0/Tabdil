package com.example.tabdil.data.model.remote

import com.google.gson.annotations.SerializedName as SN

data class PariCurrency(
    @SN("id")
    val id: Int,

    @SN("name")
    val name: String,

    @SN("name_fa")
    val persianName: String,

    @SN("symbol")
    val symbol: String
)