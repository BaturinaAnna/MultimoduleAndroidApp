package ru.ozon.route256.core_network_impl.data.workers

import android.content.Context
import androidx.work.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ru.ozon.route256.core_network_api.di.NetworkComponent
import ru.ozon.route256.core_network_api.service.RetrofitClientInstance
import ru.ozon.route256.core_utils.constants.ProductsInListWorkerConstants.KEY_PRODUCTS_IN_LIST_RESPONSE
import ru.ozon.route256.core_utils.constants.ProductsInListWorkerConstants.TAG_PRODUCTS_IN_LIST_WORKER
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsInListWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val productsInListRequestPeriodic =
            OneTimeWorkRequest.Builder(ProductsInListWorker::class.java)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .build()

        NetworkComponent.workManager?.enqueueUniqueWork(
            TAG_PRODUCTS_IN_LIST_WORKER,
            ExistingWorkPolicy.REPLACE,
            productsInListRequestPeriodic
        )

        val result = CoroutineScope(Dispatchers.IO).async {
            val response = RetrofitClientInstance.api
                .getProductsInList()

            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                val output = Data.Builder()
                    .putString(KEY_PRODUCTS_IN_LIST_RESPONSE, Gson().toJson(body))
                    .build()
                Result.success(output)
            } else {
                Result.failure()
            }
        }
        return result.await()
    }
}