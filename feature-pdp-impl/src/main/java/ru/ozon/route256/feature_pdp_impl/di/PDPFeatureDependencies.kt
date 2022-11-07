package ru.ozon.route256.feature_pdp_impl.di

import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productsApi(): ProductsApi
    fun pdpNavigation(): PDPNavigationApi
    fun localDatabaseApi(): LocalDatabaseApi
}
