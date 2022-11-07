package ru.ozon.route256.core_network_api.di

import androidx.work.WorkManager

object NetworkInjectorProxy {
    fun initWorkManager(workManager: WorkManager) {
        NetworkComponent.initAndGet(workManager)
    }
}