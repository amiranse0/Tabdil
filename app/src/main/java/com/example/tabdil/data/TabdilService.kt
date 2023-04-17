package com.example.tabdil.data

import com.example.tabdil.data.model.TokensItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TabdilService {
    @GET("r/plots/currency_prices/")
    suspend fun getCurrencies(
        @QueryMap query: Map<String, String>
    ): List<TokensItem>

    companion object {
        fun create(): TabdilService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(ApiConfigurations.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TabdilService::class.java)
        }
    }
}