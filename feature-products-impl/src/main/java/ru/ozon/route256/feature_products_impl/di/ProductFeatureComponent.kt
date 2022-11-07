package ru.ozon.route256.feature_products_impl.di

import dagger.Component
import ru.ozon.route256.core_local_storage_api.LocalStorageApi
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_product_recommendation_api.ProductRecommendationApi
import ru.ozon.route256.core_product_recommendation_api.RecommendationApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [ProductFeatureDependencies::class]
)
@PerFeature
abstract class ProductFeatureComponent {

    companion object {

        @Volatile
        var productFeatureComponent: ProductFeatureComponent? = null
            private set

        fun initAndGet(productFeatureDependencies: ProductFeatureDependencies): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                synchronized(ProductFeatureComponent::class) {
                    productFeatureComponent = DaggerProductFeatureComponent.builder()
                        .productFeatureDependencies(productFeatureDependencies)
                        .build()
                }
            }
            return productFeatureComponent
        }

        fun get(): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                throw RuntimeException("you must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return productFeatureComponent
        }

        fun resetComponent() {
            productFeatureComponent = null
        }
    }

    abstract fun inject(fragment: ProductsFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, ProductNavigationApi::class, LocalStorageApi::class, RecommendationApi::class])
    interface ProductFeatureDependenciesComponent : ProductFeatureDependencies

}