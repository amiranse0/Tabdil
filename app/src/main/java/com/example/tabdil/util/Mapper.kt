package com.example.tabdil.util

import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.data.model.remote.Currency

object Mapper {
    fun remoteToLocalCurrency(remoteCurrency: Currency, isPin: Boolean, isFavorite: Boolean): LocalCurrency {
        val markets = remoteCurrency.markets
        val priceInTether = markets.let {
            var price: Double = -1.0
            for (index in it.indices) {
                if (it[index].firstCurrency.symbol == "USDT" || it[index].secondCurrency.symbol == "USDT") {
                    price = it[index].price.toDouble()
                    break
                }
            }
            price
        }

        val secondary = markets.let {
            var price: Double = -1.0
            var symbol: String = "-"
            for (index in it.indices) {
                if (it[index].firstCurrency.symbol != "USDT" && it[index].secondCurrency.symbol != "USDT") {
                    price = it[index].price.toDouble()
                    symbol = it[index].symbol
                }
            }
            Pair(price, symbol)
        }

        return LocalCurrency(
            id = remoteCurrency.id,
            name = remoteCurrency.name,
            persianName = remoteCurrency.persianName,
            priceInTether = priceInTether,
            secondaryPrice = secondary.first,
            secondarySymbolPrice = secondary.second,
            changePercent = remoteCurrency.changePercent.toDouble(),
            isPin = isPin,
            isFavorite = isFavorite
        )
    }
}