package ru.ozon.route256.core_network_impl.data

import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_api.di.NetworkComponent
import ru.ozon.route256.core_network_impl.data.workers.ProductsInListWorker
import ru.ozon.route256.core_network_impl.data.workers.ProductsWorker
import ru.ozon.route256.core_utils.constants.ProductsInListWorkerConstants.TAG_PRODUCTS_IN_LIST_WORKER
import ru.ozon.route256.core_utils.constants.ProductsWorkerConstants.TAG_PRODUCTS_WORKER
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsApiImpl @Inject constructor() : ProductsApi {

    override fun getProducts() {
        val productsInListRequest = OneTimeWorkRequest.Builder(ProductsInListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag(TAG_PRODUCTS_IN_LIST_WORKER)
            .build()

        val productsRequest = OneTimeWorkRequest.Builder(ProductsWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .addTag(TAG_PRODUCTS_WORKER)
            .build()

        NetworkComponent.workManager
            ?.beginWith(productsInListRequest)
            ?.then(productsRequest)
            ?.enqueue()
    }
}