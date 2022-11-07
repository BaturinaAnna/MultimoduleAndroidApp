package ru.ozon.route256.core_local_storage_api.di

import android.content.Context

object LocalStorageInjectorProxy {
    fun initContext(context: Context) {
        LocalStorageComponent.initAndGet(context)
    }
}