package ru.ozon.route256.core_product_recommendation.di

import dagger.Component
import ru.ozon.route256.core_product_recommendation_api.RecommendationApi
import javax.inject.Singleton

@Singleton
@Component(modules = [RecommendationModule::class])
interface CoreProductRecommendationComponent: RecommendationApi