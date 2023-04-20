package com.example.tabdil.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow

fun haveNetwork(context: Context):MutableStateFlow<Boolean>{
    val isConnectStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)

    val networkRequest = NetworkRequest.Builder().build()

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isConnectStateFlow.value = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isConnectStateFlow.value = false
            Toast.makeText(context, "no connection", Toast.LENGTH_LONG).show()
        }
    }

    val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    connectivityManager.requestNetwork(networkRequest, networkCallback)

    return isConnectStateFlow

}