package ru.ozon.route256.feature_new_product_impl.data.repository_impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.core_utils.mappers.toProduct
import ru.ozon.route256.feature_new_product_impl.data.repository_impl.mapper.toProductInList
import ru.ozon.route256.feature_new_product_impl.domain.repository.NewProductRepository
import javax.inject.Inject

class NewProductRepositoryImpl @Inject constructor(
    private val localDatabaseApi: LocalDatabaseApi
) : NewProductRepository {

    override fun addProduct(newProduct: ProductDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            localDatabaseApi.saveProductsInList(mutableListOf(newProduct.toProductInList()))
            localDatabaseApi.saveProducts(mutableListOf(newProduct.toProduct()))
        }
    }
}