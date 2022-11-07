package ru.ozon.route256.core_local_storage_impl.di

import dagger.Component
import ru.ozon.route256.core_local_storage_api.LocalStorageApi
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalStorageModule::class])
interface CoreLocalStorageComponent : LocalStorageApi
