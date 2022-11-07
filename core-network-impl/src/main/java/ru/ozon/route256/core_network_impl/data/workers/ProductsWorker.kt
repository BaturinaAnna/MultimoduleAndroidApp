package ru.ozon.route256.core_network_impl.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.google.gson.Gson
import kotlinx.coroutines.*
import ru.ozon.route256.core_network_api.service.RetrofitClientInstance
import ru.ozon.route256.core_utils.constants.ProductsWorkerConstants.KEY_PRODUCTS_RESPONSE
import javax.inject.Inject

class ProductsWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val result = CoroutineScope(Dispatchers.IO).async {
            val response = RetrofitClientInstance.api
                .getProducts()

            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                val output = Data.Builder()
                    .putString(KEY_PRODUCTS_RESPONSE, Gson().toJson(body))
                    .build()
                Result.success(output)
            } else {
                Result.failure()
            }
        }
        return result.await()
    }

}