package com.example.tabdil.data

import com.example.tabdil.util.ResultOf
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getData(query: Map<String, String>)= flow {
        try {
            val data = remoteDataSource.fetch(query)
            emit(ResultOf.Success(data))
        } catch (e:Exception){
            emit(ResultOf.Error(e))
        }
    }
}