package ru.ozon.route256.feature_profile_api

import androidx.fragment.app.Fragment

interface ProfileNavigationApi {
    fun isFeatureClosed(fragment: Fragment): Boolean
}