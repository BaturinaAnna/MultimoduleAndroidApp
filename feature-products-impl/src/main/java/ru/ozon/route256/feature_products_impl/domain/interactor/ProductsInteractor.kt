package ru.ozon.route256.feature_products_impl.domain.interactor

import androidx.lifecycle.LiveData
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProfileVO

interface ProductsInteractor {
    suspend fun getProducts(): List<ProductInListVO>
    fun addToCart(product: ProductInListVO)
    fun getRecommendedProduct(inputParams: List<Int>): LiveData<String>
    suspend fun getProfile(): ProfileVO?
}