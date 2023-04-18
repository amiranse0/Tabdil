package com.example.tabdil.data

import com.example.tabdil.data.model.remote.Currency
import com.example.tabdil.util.ResultOf
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
    suspend fun getData(query: Map<String, String>)= flow {
        while (true){
            emit(ResultOf.LoadingEmptyLocal)
            if(!localDataSource.isLocalEmpty()){
                emit(ResultOf.LoadingFillLocal)
                val currencies = localDataSource.getLocalCurrencies()
                currencies.collect{
                    emit(ResultOf.Success(it))
                }
            }
            try {
                val data = fetch(query)
                saveDataToLocal(data)

                emit(ResultOf.LoadingFillLocal)
                val currencies = localDataSource.getLocalCurrencies()
                currencies.collect{
                    emit(ResultOf.Success(it))
                }

            }catch (e: Exception){
                if (localDataSource.isLocalEmpty()) emit(ResultOf.ErrorEmptyLocal(e))
                else emit(ResultOf.ErrorFillLocal(e))
            }
            kotlinx.coroutines.delay(TIME_STEP)
        }
    }
}