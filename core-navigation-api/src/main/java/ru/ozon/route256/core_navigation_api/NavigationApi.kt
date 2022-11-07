package ru.ozon.route256.core_navigation_api

import ru.ozon.route256.feature_new_product_api.NewProductNavigationApi
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_profile_api.ProfileNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    fun getPDPNavigation(): PDPNavigationApi
    fun getNewProductNavigation(): NewProductNavigationApi
    fun getProfileNavigationApi(): ProfileNavigationApi
}