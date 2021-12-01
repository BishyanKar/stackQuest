package com.example.stackoverquestions.networkutil

import android.content.Context
import android.net.*
import android.net.ConnectivityManager.NetworkCallback
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import java.lang.Exception


class NetworkChecker(
    private val mContext: Context,
    private val networkState: MutableLiveData<Boolean>
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun registerNetworkCallBack() {
        try {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()
            cm.registerDefaultNetworkCallback(object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    networkState.postValue(true)
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    networkState.postValue(false)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                }

                override fun onLinkPropertiesChanged(
                    network: Network,
                    linkProperties: LinkProperties
                ) {
                    super.onLinkPropertiesChanged(network, linkProperties)
                }

                override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
                    super.onBlockedStatusChanged(network, blocked)
                }
            })
            networkState.postValue(false)
        } catch (e: Exception) {
            Timber.d("registerNetworkCallBack: ERROR : $e")
            networkState.postValue(false)
        }
    }
}