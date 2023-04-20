package com.example.tabdil.data

import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.data.model.remote.Currency
import com.example.tabdil.util.ResultOf
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    companion object {
        val TIME_STEP = TimeUnit.MINUTES.toMillis(1)
    }

    private suspend fun fetch(query: Map<String, String>) = remoteDataSource.fetch(query)

    private suspend fun saveDataToLocal(data: List<Currency>) {
        localDataSource.updateCurrencies(data)
    }

    suspend fun getDataFromLocal() = flow{
        val currencies = localDataSource.getLocalCurrencies()
        currencies.collect {
            emit(it)
        }
    }

    suspend fun getData(query: Map<String, String>) = flow {
        while (true) {
            emit(ResultOf.Loading)
            try{
                val data = fetch(query)
                saveDataToLocal(data)
                getDataFromLocal().collect{
                    emit(ResultOf.Success(it))
                }
            }catch (e:Exception){
                emit(ResultOf.Error(e))
            }

            kotlinx.coroutines.delay(TIME_STEP)
        }
    }

    suspend fun pinUnpinCurrency(currency: LocalCurrency) {
        localDataSource.pinUnpinCurrency(currency)
    }

    suspend fun favoriteUnfavoriteCurrency(currency: LocalCurrency) {
        localDataSource.favoriteUnfavoriteCurrency(currency)
    }

    suspend fun getFavoritesName() = localDataSource.getFavoritesName()
}