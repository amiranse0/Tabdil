package com.example.tabdil.data

import android.util.Log
import androidx.room.withTransaction
import com.example.tabdil.data.db.TabdilDatabase
import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.data.model.remote.Currency
import com.example.tabdil.util.Mapper
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: TabdilDatabase
) {
    suspend fun pinUnpinCurrency(currency: LocalCurrency) {
        database.currencyDao().updateCurrency(currency)
    }

    suspend fun favoriteUnfavoriteCurrency(currency: LocalCurrency) {
        database.currencyDao().updateCurrency(currency)
    }

    suspend fun updateCurrencies(remoteCurrencies: List<Currency>) {
        database.withTransaction {
            val pinnedIds: List<Int> = database.currencyDao().getPinnedIds()
            val favoriteIds: List<Int> = database.currencyDao().getFavoriteIds()

            for (currency in remoteCurrencies) {
                val localCurrency = Mapper.remoteToLocalCurrency(
                    currency,
                    currency.id in pinnedIds,
                    currency.id in favoriteIds
                )
                database.currencyDao().insertCurrency(localCurrency)
            }
        }
    }

    fun getLocalCurrencies() = database.currencyDao().getCurrencies()

    suspend fun isLocalEmpty() = database.currencyDao().getNumberOfRecords() == 0

    suspend fun getFavoritesName() = database.currencyDao().getFavoritesName()
}