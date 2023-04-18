package com.example.tabdil.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.tabdil.data.model.local.LocalCurrency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(vararg currency: LocalCurrency)

    @Delete
    suspend fun deleteCurrency(vararg currency: LocalCurrency)

    @Query("SELECT * FROM currency ORDER BY :is_pin")
    fun getDCurrencies(): Flow<List<LocalCurrency>>

    @Update
    suspend fun updateCurrency(vararg currency: LocalCurrency)
}