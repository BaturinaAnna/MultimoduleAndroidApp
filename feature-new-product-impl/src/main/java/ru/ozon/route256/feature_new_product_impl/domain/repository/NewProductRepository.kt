package ru.ozon.route256.feature_new_product_impl.domain.repository

import ru.ozon.route256.core_network_api.models.ProductDTO

interface NewProductRepository {
    fun addProduct(newProduct: ProductDTO)
}