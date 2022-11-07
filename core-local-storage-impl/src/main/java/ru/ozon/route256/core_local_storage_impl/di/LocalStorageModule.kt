package ru.ozon.route256.core_local_storage_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_local_storage_impl.data.LocalDatabaseApiImpl

@Module
interface LocalStorageModule {

    @Binds
    fun bindLocalDatabaseApi(api: LocalDatabaseApiImpl): LocalDatabaseApi
}