package ru.ozon.route256.core_network_api.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {
    private val okHttpClient = OkHttpClient.Builder().build()

    val api: ServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServiceApi::class.java)
    }
}