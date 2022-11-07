package ru.ozon.route256.feature_pdp_impl.domain.interactor

import ru.ozon.route256.feature_pdp_impl.presentation.view_objects.ProductVO

interface PDPInteractor {
    suspend fun getProductById(guid: String): ProductVO?
    fun updateViewsCounterForProduct(guid: String)
}