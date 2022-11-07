package ru.ozon.route256.homework2

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

enum class NetworkStatus {
    AVAILABLE, UNAVAILABLE
}

class NetworkConnection @Inject constructor() {
    private var connectivityManager: ConnectivityManager =
        MainApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @OptIn(ExperimentalCoroutinesApi::class)
    val networkStatus = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(NetworkStatus.UNAVAILABLE)
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.AVAILABLE)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.UNAVAILABLE)
            }
        }

        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),
            networkCallback
        )

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
}

@FlowPreview
inline fun <Result> Flow<NetworkStatus>.map(
    crossinline onUnavailable: suspend () -> Result,
    crossinline onAvailable: suspend () -> Result,
): Flow<Result> = map { status ->
    when (status) {
        NetworkStatus.UNAVAILABLE -> onUnavailable()
        NetworkStatus.AVAILABLE -> onAvailable()
    }
}