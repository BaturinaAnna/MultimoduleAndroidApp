package ru.ozon.route256.core_product_recommendation.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_product_recommendation.ProductRecommendationApiImpl
import ru.ozon.route256.core_product_recommendation_api.ProductRecommendationApi

@Module
interface RecommendationModule {

    @Binds
    fun bindProductRecommendationApi(api: ProductRecommendationApiImpl): ProductRecommendationApi
}