package com.sample.zoopark.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.sample.zoopark.ZooParkApplication

object NetworkUtil {
    const val TAG = "NetworkUtil"
    fun isInternetConnected(): Boolean {
        val connectivityManager = ZooParkApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network= connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
    fun getString(resourceId: Int): String {
        return ZooParkApplication.instance.getString(resourceId)
    }

 }