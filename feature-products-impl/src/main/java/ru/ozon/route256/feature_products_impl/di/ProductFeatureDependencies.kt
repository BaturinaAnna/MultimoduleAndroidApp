package ru.ozon.route256.feature_products_impl.di

import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_product_recommendation_api.ProductRecommendationApi
import ru.ozon.route256.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productsApi(): ProductsApi
    fun productNavigationApi(): ProductNavigationApi
    fun localDatabaseApi(): LocalDatabaseApi
    fun productRecommendationApi(): ProductRecommendationApi
}