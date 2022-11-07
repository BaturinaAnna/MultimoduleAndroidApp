package ru.ozon.route256.core_network_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface CoreNetworkComponent: NetworkApi