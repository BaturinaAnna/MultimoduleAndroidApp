package ru.ozon.route256.feature_new_product_api

import androidx.fragment.app.Fragment

interface NewProductNavigationApi {
    fun isFeatureClosed(fragment: Fragment): Boolean
}