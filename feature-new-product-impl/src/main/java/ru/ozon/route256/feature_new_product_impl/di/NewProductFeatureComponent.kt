package ru.ozon.route256.feature_new_product_impl.di

import dagger.Component
import ru.ozon.route256.core_local_storage_api.LocalStorageApi
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_new_product_api.NewProductNavigationApi
import ru.ozon.route256.feature_new_product_impl.presentation.view.NewProductFragment

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [NewProductFeatureDependencies::class]
)
@PerFeature
abstract class NewProductFeatureComponent {
    companion object {

        @Volatile
        var newProductFeatureComponent: NewProductFeatureComponent? = null
            private set

        fun initAndGet(newProductFeatureDependencies: NewProductFeatureDependencies): NewProductFeatureComponent? {
            if (newProductFeatureComponent == null) {
                synchronized(NewProductFeatureComponent::class) {
                    newProductFeatureComponent = DaggerNewProductFeatureComponent.builder()
                        .newProductFeatureDependencies(newProductFeatureDependencies)
                        .build()
                }
            }
            return newProductFeatureComponent
        }

        fun get(): NewProductFeatureComponent? {
            if (newProductFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return newProductFeatureComponent
        }

        fun resetComponent() {
            newProductFeatureComponent = null
        }

    }

    abstract fun inject(fragment: NewProductFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, NewProductNavigationApi::class, LocalStorageApi::class])
    interface PDPFeatureDependenciesComponent : NewProductFeatureDependencies
}