package ru.ozon.route256.feature_products_impl.domain.interactor

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.feature_products_impl.domain.mapper.toProductInListDTO
import ru.ozon.route256.feature_products_impl.domain.mapper.toProductInListVO
import ru.ozon.route256.feature_products_impl.domain.mapper.toProfileVO
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProfileVO
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : ProductsInteractor {

    override suspend fun getProducts(): List<ProductInListVO> =
        withContext(Dispatchers.IO) {
            productsRepository.getProducts().map { productInListDTO ->
                productInListDTO.toProductInListVO(
                    productsRepository.getViewsCounters()
                        .find { it.guid == productInListDTO.guid }?.viewCounter ?: 0
                )
            }
        }

    override fun addToCart(product: ProductInListVO) {
        productsRepository.addToCart(product.toProductInListDTO())
    }

    override fun getRecommendedProduct(inputParams: List<Int>): LiveData<String> {
        return productsRepository.getRecommendedProduct(inputParams)
    }

    override suspend fun getProfile(): ProfileVO? =
        withContext(Dispatchers.IO) {
            productsRepository.getProfile()?.toProfileVO()
        }
}