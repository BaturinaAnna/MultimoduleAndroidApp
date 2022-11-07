package ru.ozon.route256.feature_new_product_impl.presentation.view_models

import androidx.lifecycle.ViewModel
import ru.ozon.route256.feature_new_product_impl.domain.interactor.NewProductInteractor
import ru.ozon.route256.feature_new_product_impl.presentation.view_objects.ProductVO
import java.util.*
import javax.inject.Inject

class NewProductViewModel @Inject constructor(
    private val newProductInteractor: NewProductInteractor
) : ViewModel() {

    fun addNewProduct(name: String, price: String, description: String) {
        val productToAdd = ProductVO(
            guid = UUID.randomUUID().toString(),
            name = name,
            price = price,
            description = description,
            rating = 0.0,
            isFavorite = false,
            isInCart = false,
            images = emptyList(),
            weight = 0.0,
            count = 0,
            availableCount = 0,
            additionalParams = emptyMap()
        )
        newProductInteractor.addProduct(productToAdd)
    }
}