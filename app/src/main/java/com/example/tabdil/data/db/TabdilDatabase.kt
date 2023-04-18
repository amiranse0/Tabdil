package com.example.tabdil.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tabdil.data.model.local.LocalCurrency

@Database(entities = [LocalCurrency::class], version = 1)
abstract class TabdilDatabase:RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object{
        @Volatile
        private var INSTANCE: TabdilDatabase? = null

        fun getDatabase(context: Context): TabdilDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            } else{
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TabdilDatabase::class.java,
                        "tabdil_database",
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}