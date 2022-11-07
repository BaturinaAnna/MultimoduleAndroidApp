package ru.ozon.route256.feature_new_product_impl.di

import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_new_product_api.NewProductNavigationApi

interface NewProductFeatureDependencies {
    fun productsApi(): ProductsApi
    fun newProductNavigationApi(): NewProductNavigationApi
    fun localDatabaseApi(): LocalDatabaseApi
}