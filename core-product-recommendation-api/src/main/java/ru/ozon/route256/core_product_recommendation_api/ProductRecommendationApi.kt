package ru.ozon.route256.core_product_recommendation_api

import androidx.lifecycle.LiveData

interface ProductRecommendationApi {
    fun makeRecommendation(inputParams: List<Int>): LiveData<String>
}