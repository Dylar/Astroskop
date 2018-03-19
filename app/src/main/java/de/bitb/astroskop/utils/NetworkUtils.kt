package de.bitb.astroskop.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import javax.inject.Inject

class NetworkUtils @Inject
constructor(private val context: Context) {

    val isWifiConnected: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

            return networkInfo.isConnectedOrConnecting
        }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

}