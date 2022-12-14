package ru.ozon.route256.feature_products_api

import androidx.fragment.app.Fragment

interface ProductNavigationApi {
    fun navigateToPDP(fragment: Fragment, guid: String)
    fun navigateToNewProduct(fragment: Fragment)
    fun navigateToProfile(fragment: Fragment)
    fun isFeatureClosed(fragment: Fragment): Boolean
}