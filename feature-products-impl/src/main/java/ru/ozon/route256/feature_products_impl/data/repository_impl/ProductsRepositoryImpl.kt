package ru.ozon.route256.feature_products_impl.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.WorkInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_local_storage_api.models.Profile
import ru.ozon.route256.core_local_storage_api.models.ViewCountersTuple
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_api.di.NetworkComponent
import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.core_network_api.models.ProductInListDTO
import ru.ozon.route256.core_product_recommendation_api.ProductRecommendationApi
import ru.ozon.route256.core_utils.constants.ProductsInListWorkerConstants.KEY_PRODUCTS_IN_LIST_RESPONSE
import ru.ozon.route256.core_utils.constants.ProductsInListWorkerConstants.TAG_PRODUCTS_IN_LIST_WORKER
import ru.ozon.route256.core_utils.constants.ProductsWorkerConstants
import ru.ozon.route256.core_utils.constants.ProductsWorkerConstants.KEY_PRODUCTS_RESPONSE
import ru.ozon.route256.core_utils.mappers.toProduct
import ru.ozon.route256.core_utils.mappers.toProductInList
import ru.ozon.route256.core_utils.mappers.toProductInListDTO
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import java.lang.reflect.Type
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi,
    private val localDatabaseApi: LocalDatabaseApi,
    private val productRecommendationApi: ProductRecommendationApi
) : ProductsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getProducts(): List<ProductInListDTO> =
        suspendCancellableCoroutine { continuation ->
            productsApi.getProducts()
            CoroutineScope(Dispatchers.Main).launch {
                NetworkComponent.workManager
                    ?.getWorkInfosByTagLiveData(ProductsWorkerConstants.TAG_PRODUCTS_WORKER)
                    ?.observeForever { workInfos ->
                        val workInfoProducts = workInfos[0]
                        when (workInfoProducts?.state) {
                            WorkInfo.State.SUCCEEDED -> {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val products = getProductsFromServerResponse(workInfoProducts)
                                    localDatabaseApi.saveProducts(
                                        products.map { it.toProduct() }
                                    )
                                }
                            }
                            else -> {}
                        }
                    }

                NetworkComponent.workManager
                    ?.getWorkInfosByTagLiveData(TAG_PRODUCTS_IN_LIST_WORKER)
                    ?.observeForever { workInfos ->
                        val workInfoProductInList = workInfos[0]
                        when (workInfoProductInList?.state) {
                            WorkInfo.State.SUCCEEDED -> {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val products =
                                        getProductsInListFromServerResponse(workInfoProductInList)
                                    localDatabaseApi.saveProductsInList(
                                        products.map { it.toProductInList() }
                                    )
                                    products.addAll(
                                        localDatabaseApi.getProductsInList()
                                            .map { it.toProductInListDTO() }
                                            .filter { !products.contains(it) })
                                    if (continuation.isActive) {
                                        continuation.resume(products, null)
                                    }
                                }
                            }
                            WorkInfo.State.FAILED -> {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val products = localDatabaseApi.getProductsInList()
                                    products.toMutableList()
                                        .addAll(localDatabaseApi.getProductsInList())
                                    if (continuation.isActive) {
                                        continuation.resume(
                                            products.map { it.toProductInListDTO() },
                                            null
                                        )
                                    }
                                }
                            }
                            else -> {
                                if (continuation.isActive) {
                                    continuation.resume(mutableListOf(), null)
                                }
                            }
                        }
                    }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getViewsCounters(): List<ViewCountersTuple> =
        suspendCancellableCoroutine { continuation ->
            continuation.resume(localDatabaseApi.getViewsCounters(), null)
        }

    override fun addToCart(product: ProductInListDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            localDatabaseApi.addToCart(product.guid)
        }
    }

    override fun getRecommendedProduct(inputParams: List<Int>): LiveData<String> {
        val recommendation: MutableLiveData<String> = MutableLiveData()
        productRecommendationApi.makeRecommendation(inputParams).observeForever { result ->
            recommendation.postValue(result)
        }
        return recommendation
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getProfile(): Profile? =
        suspendCancellableCoroutine { continuation ->
            continuation.resume(localDatabaseApi.getPersonProfile(), null)
        }

    private fun getProductsInListFromServerResponse(workInfo: WorkInfo): ArrayList<ProductInListDTO> {
        val productsListType: Type = object : TypeToken<List<ProductInListDTO>>() {}.type
        return Gson().fromJson(
            workInfo.outputData.keyValueMap[KEY_PRODUCTS_IN_LIST_RESPONSE].toString(),
            productsListType
        )
    }

    private fun getProductsFromServerResponse(workInfo: WorkInfo): ArrayList<ProductDTO> {
        val productsListType: Type = object : TypeToken<List<ProductDTO>>() {}.type
        return Gson().fromJson(
            workInfo.outputData.keyValueMap[KEY_PRODUCTS_RESPONSE].toString(),
            productsListType
        )
    }
}