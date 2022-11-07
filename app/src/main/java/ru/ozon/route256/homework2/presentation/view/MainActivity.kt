package ru.ozon.route256.homework2.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.ozon.route256.core_local_storage_api.di.LocalStorageInjectorProxy
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.core_network_api.di.NetworkInjectorProxy
import ru.ozon.route256.core_product_recommendation_api.di.ProductRecommendationInjectorProxy
import ru.ozon.route256.homework2.MainApplication
import ru.ozon.route256.homework2.NetworkConnection
import ru.ozon.route256.homework2.R
import ru.ozon.route256.homework2.map

enum class NetworkConnectionState {
    CONNECTED, DISCONNECTED
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureInjectorProxy.initFeatureProductsDI()
        NetworkInjectorProxy.initWorkManager(MainApplication.workManager)
        LocalStorageInjectorProxy.initContext(applicationContext)
        ProductRecommendationInjectorProxy.initContext(applicationContext)
        setContentView(R.layout.activity_main)
        val notification = findViewById<View>(R.id.notification_no_internet_connection)

        val networkConnection = NetworkConnection()
        val state =
            networkConnection.networkStatus
                .map(
                    onAvailable = { NetworkConnectionState.CONNECTED },
                    onUnavailable = { NetworkConnectionState.DISCONNECTED },
                )
                .asLiveData(Dispatchers.IO)

        state.observe(this) { networkConnection ->
            when (networkConnection) {
                NetworkConnectionState.CONNECTED -> {
//                    notification.visibility = View.GONE
                }
                NetworkConnectionState.DISCONNECTED -> {
//                    notification.visibility = View.VISIBLE
                }
            }
        }
    }
}