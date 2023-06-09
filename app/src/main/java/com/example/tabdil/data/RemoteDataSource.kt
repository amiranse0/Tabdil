package com.example.tabdil.data

import com.example.tabdil.data.model.remote.Currency
import com.example.tabdil.data.remoteservice.TabdilService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: TabdilService
) {
    suspend fun fetch(query: Map<String, String>): List<Currency>{
        return service.getCurrencies(query)
    }
}