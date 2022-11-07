package ru.ozon.route256.core_product_recommendation

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tensorflow.lite.Interpreter
import ru.ozon.route256.core_product_recommendation_api.ProductRecommendationApi
import ru.ozon.route256.core_product_recommendation_api.di.ProductRecommendationComponent.Companion.context
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class ProductRecommendationApiImpl @Inject constructor() : ProductRecommendationApi {
    private val TAG = "ProductRecommendationApiImpl"
    private var interpreter: Interpreter? = null
    var isInitialized = false
        private set

    private val executorService: ExecutorService = Executors.newCachedThreadPool()

    override fun makeRecommendation(inputParams: List<Int>): LiveData<String> {
        val result: MutableLiveData<String> = MutableLiveData()
        if (!isInitialized) {
            executorService.execute {
                try {
                    initializeInterpreter(context)
                    result.postValue(recommend(inputParams))
                } catch (e: IOException) {
                    Log.d(TAG, e.message ?: "")
                }
            }
        } else {
            executorService.execute {
                result.postValue(recommend(inputParams))
            }
        }
        return result
    }

    @Throws(IOException::class)
    private fun initializeInterpreter(context: Context) {
        val assetManager = context.assets
        val model = loadModelFile(assetManager, "fav_prod.tflite")
        interpreter = Interpreter(model)
        isInitialized = true
    }

    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager, filename: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(filename)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun recommend(inputParams: List<Int>): String? {
        check(isInitialized) { "TF Lite Interpreter is not initialized yet." }
        val inputs: Array<FloatArray> = arrayOf(inputParams.map { it.toFloat() }.toFloatArray())
        val output: Array<FloatArray> = arrayOf(floatArrayOf(0.0f))
        interpreter?.run(inputs, output)
        return mapIntResultToProductId[output[0][0]]
    }

    companion object {
        val mapIntResultToProductId = mapOf(
            0.0f to "b5f5852b-3b8c-4857-9f53-ac5c2b6a3b2f",
            1.0f to "5cbbb7d0-e9c6-4932-8d85-79313c58e465",
            2.0f to "cc87f70d-7570-42ee-a221-db8887534896",
            3.0f to "627de4ca-b4fd-46ea-9201-562448af6fc1",
            4.0f to "493ef2ba-cd2f-4ca5-b3af-f9091115700e",
            5.0f to "ceadee7a-9d10-4987-8ed3-0624d6fbe5c0",
            6.0f to "c3cfe1a8-6eec-4e9f-a260-490e128763f4",
            7.0f to "a9cd4415-48b0-4557-8f29-6d28824fe91b",
            8.0f to "856c1c90-1b8e-46ba-a5de-bc818dc1bdbd",
            9.0f to "ebe799f5-6e01-45dc-8139-e714753896c1"
        )
    }
}