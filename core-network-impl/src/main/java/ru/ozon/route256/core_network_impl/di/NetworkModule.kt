package ru.ozon.route256.core_network_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_impl.data.ProductsApiImpl

@Module
interface NetworkModule {

    @Binds
    fun bindProductsApi(api: ProductsApiImpl): ProductsApi
}