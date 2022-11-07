package ru.ozon.route256.feature_products_impl.domain.repository

import androidx.lifecycle.LiveData
import ru.ozon.route256.core_local_storage_api.models.Profile
import ru.ozon.route256.core_local_storage_api.models.ViewCountersTuple
import ru.ozon.route256.core_network_api.models.ProductInListDTO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProfileVO

interface ProductsRepository {
    suspend fun getProducts(): List<ProductInListDTO>
    suspend fun getViewsCounters(): List<ViewCountersTuple>
    fun addToCart(product: ProductInListDTO)
    fun getRecommendedProduct(inputParams: List<Int>): LiveData<String>
    suspend fun getProfile(): Profile?
}