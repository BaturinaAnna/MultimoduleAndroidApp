package ru.ozon.route256.core_navigation_impl.di

import ru.ozon.route256.core_local_storage_impl.di.DaggerCoreLocalStorageComponent
import ru.ozon.route256.core_network_impl.di.DaggerCoreNetworkComponent
import ru.ozon.route256.core_product_recommendation.di.DaggerCoreProductRecommendationComponent
import ru.ozon.route256.feature_new_product_impl.di.DaggerNewProductFeatureComponent_PDPFeatureDependenciesComponent
import ru.ozon.route256.feature_new_product_impl.di.NewProductFeatureComponent
import ru.ozon.route256.feature_pdp_impl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import ru.ozon.route256.feature_pdp_impl.di.PDPFeatureComponent
import ru.ozon.route256.feature_products_impl.di.DaggerProductFeatureComponent_ProductFeatureDependenciesComponent
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent
import ru.ozon.route256.feature_profile.di.DaggerProfileFeatureComponent_ProfileFeatureDependenciesComponent
import ru.ozon.route256.feature_profile.di.ProfileFeatureComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI() {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductNavigation()
                )
                .localStorageApi(DaggerCoreLocalStorageComponent.builder().build())
                .recommendationApi(DaggerCoreProductRecommendationComponent.builder().build())
                .build()
        )
    }

    fun initFeaturePDPDI() {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .pDPNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getPDPNavigation()
                )
                .localStorageApi(DaggerCoreLocalStorageComponent.builder().build())
                .build()
        )
    }

    fun initFeatureNewProductDI() {
        NewProductFeatureComponent.initAndGet(
            DaggerNewProductFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .newProductNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getNewProductNavigation()
                )
                .localStorageApi(DaggerCoreLocalStorageComponent.builder().build())
                .build()
        )
    }

    fun initFeatureProfileDI() {
        ProfileFeatureComponent.initAndGet(
            DaggerProfileFeatureComponent_ProfileFeatureDependenciesComponent.builder()
                .localStorageApi(DaggerCoreLocalStorageComponent.builder().build())
                .profileNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProfileNavigationApi()
                )
                .build()
        )
    }
}