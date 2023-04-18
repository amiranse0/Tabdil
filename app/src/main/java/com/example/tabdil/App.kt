package com.example.tabdil

import android.app.Application
import android.content.Context
import com.example.tabdil.util.haveNetwork
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow

@HiltAndroidApp
class App:Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object{
        lateinit var appContext: Context
        fun isConnected(): MutableStateFlow<Boolean> = haveNetwork(appContext)
    }
}