package com.example.tabdil.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getData(query: Map<String, String>) = remoteDataSource.fetch(query)
}