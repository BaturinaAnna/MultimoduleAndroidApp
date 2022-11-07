package ru.ozon.route256.core_navigation_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_navigation_impl.navigation.NewProductNavigationImpl
import ru.ozon.route256.core_navigation_impl.navigation.PDPNavigationImpl
import ru.ozon.route256.core_navigation_impl.navigation.ProductNavigationImpl
import ru.ozon.route256.core_navigation_impl.navigation.ProfileNavigationImpl
import ru.ozon.route256.feature_new_product_api.NewProductNavigationApi
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_profile_api.ProfileNavigationApi

@Module
interface NavigationModule {

    @Binds
    fun bindProductNavigation(navigation: ProductNavigationImpl): ProductNavigationApi

    @Binds
    fun bindPdpNavigation(navigation: PDPNavigationImpl): PDPNavigationApi

    @Binds
    fun getNewProductNavigation(navigation: NewProductNavigationImpl): NewProductNavigationApi

    @Binds
    fun getProfileNavigation(navigation: ProfileNavigationImpl): ProfileNavigationApi
}