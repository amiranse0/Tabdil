package com.example.tabdil.data

import com.example.tabdil.data.remoteservice.TabdilService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: TabdilService
) {
    suspend fun fetch(query: Map<String, String>){
        service.getCurrencies(query)
    }
}