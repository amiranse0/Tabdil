package com.example.tabdil.data.model.local

import androidx.room.ColumnInfo as CI
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class LocalCurrency(
    @PrimaryKey(autoGenerate = false)
    @CI("id")
    val id: Int,

    @CI("name")
    val name: String,

    @CI("name_fa")
    val persianName: String,

    @CI("price_in_tether")
    val priceInTether: String,

    @CI("secondary_price")
    val secondaryPrice: String,

    @CI("change_percent")
    val changePercent: String,

    @CI("secondary_symbol_price")
    val secondarySymbolPrice: String,

    @CI("is_pin")
    var isPin: Boolean = false,

    @CI("is_favorite")
    val isFavorite: Boolean = false
)
