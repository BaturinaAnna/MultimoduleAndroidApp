package ru.ozon.route256.feature_new_product_impl.domain.interactor

import ru.ozon.route256.feature_new_product_impl.domain.mapper.toProductDTO
import ru.ozon.route256.feature_new_product_impl.domain.repository.NewProductRepository
import ru.ozon.route256.feature_new_product_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class NewProductInteractorImpl @Inject constructor(
    private val newProductRepository: NewProductRepository
) : NewProductInteractor {

    override fun addProduct(newProduct: ProductVO) {
        newProductRepository.addProduct(newProduct.toProductDTO())
    }
}