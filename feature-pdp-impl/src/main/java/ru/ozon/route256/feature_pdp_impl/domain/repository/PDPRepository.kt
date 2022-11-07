package ru.ozon.route256.feature_pdp_impl.domain.repository

import ru.ozon.route256.core_network_api.models.ProductDTO

interface PDPRepository {
    suspend fun getProductById(guid: String): ProductDTO?
    fun updateViewsCounterForProduct(guid: String)
}