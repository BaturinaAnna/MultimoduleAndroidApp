package ru.ozon.route256.feature_pdp_impl.di

import dagger.Component
import ru.ozon.route256.core_local_storage_api.LocalStorageApi
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment


@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [PDPFeatureDependencies::class]
)
@PerFeature
abstract class PDPFeatureComponent {

    companion object {

        @Volatile
        var pdpFeatureComponent: PDPFeatureComponent? = null
            private set

        fun initAndGet(pdpFeatureDependencies: PDPFeatureDependencies): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                synchronized(PDPFeatureComponent::class) {
                    pdpFeatureComponent = DaggerPDPFeatureComponent.builder()
                        .pDPFeatureDependencies(pdpFeatureDependencies)
                        .build()
                }
            }
            return pdpFeatureComponent
        }

        fun get(): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return pdpFeatureComponent
        }

        fun resetComponent() {
            pdpFeatureComponent = null
        }

    }

    abstract fun inject(fragment: PDPFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, PDPNavigationApi::class, LocalStorageApi::class])
    interface PDPFeatureDependenciesComponent : PDPFeatureDependencies

}
