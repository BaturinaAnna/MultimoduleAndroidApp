package ru.ozon.route256.feature_pdp_impl.data.repository_impl

import kotlinx.coroutines.*
import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.core_utils.mappers.toProductDTO
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(
    private val localDatabaseApi: LocalDatabaseApi
) : PDPRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getProductById(guid: String): ProductDTO? =
        suspendCancellableCoroutine { continuation ->
            val products = localDatabaseApi.getProducts()
            continuation.resume(products.find { product -> product.guid == guid }?.toProductDTO(), null)
        }

    override fun updateViewsCounterForProduct(guid: String) {
        CoroutineScope(Dispatchers.IO).launch {
            localDatabaseApi.updateViewsCounterForProduct(guid)
        }
    }
}