package ru.ozon.route256.core_product_recommendation_api.di

import android.content.Context
import dagger.Component
import dagger.internal.Preconditions

@Component(dependencies = [Context::class])
abstract class ProductRecommendationComponent {
    companion object {
        @Volatile
        var instance: ProductRecommendationComponent? = null

        @Volatile
        lateinit var context: Context

        fun initAndGet(context: Context): ProductRecommendationComponent? {
            if (instance == null) {
                synchronized(ProductRecommendationComponent::class) {
                    ProductRecommendationComponent.context = context
                    instance = DaggerProductRecommendationComponent.builder()
                        .context(context)
                        .build()
                }
            }
            return instance
        }

        fun get(): ProductRecommendationComponent {
            return Preconditions.checkNotNull(instance,
                "ProductRecommendationComponent is not initialized yet. Call init first.")!!
        }
    }
}