package ru.ozon.route256.feature_new_product_impl.domain.interactor

import ru.ozon.route256.feature_new_product_impl.presentation.view_objects.ProductVO

interface NewProductInteractor {
    fun addProduct(newProduct: ProductVO)
}