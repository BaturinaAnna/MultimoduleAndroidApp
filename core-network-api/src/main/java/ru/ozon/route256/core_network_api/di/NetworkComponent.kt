package ru.ozon.route256.core_network_api.di

import androidx.work.WorkManager
import dagger.Component
import dagger.internal.Preconditions


@Component(dependencies = [WorkManager::class])
abstract class NetworkComponent {
    companion object {
        @Volatile
        var instance: NetworkComponent? = null

        @Volatile
        var workManager: WorkManager? = null

        fun initAndGet(workManager: WorkManager): NetworkComponent? {
            if (instance == null) {
                synchronized(NetworkComponent::class) {
                    NetworkComponent.workManager = workManager
                    instance = DaggerNetworkComponent.builder()
                        .workManager(workManager)
                        .build()
                }
            }
            return instance
        }

        fun get(): NetworkComponent {
            return Preconditions.checkNotNull(instance,
                "NetworkComponent is not initialized yet. Call init first.")!!
        }
    }
}