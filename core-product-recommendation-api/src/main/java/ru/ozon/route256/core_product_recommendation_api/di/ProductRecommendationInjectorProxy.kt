package ru.ozon.route256.core_product_recommendation_api.di

import android.content.Context

object ProductRecommendationInjectorProxy {
    fun initContext(context: Context) {
        ProductRecommendationComponent.initAndGet(context)
    }
}