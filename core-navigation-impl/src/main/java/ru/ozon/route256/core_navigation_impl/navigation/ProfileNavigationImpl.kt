package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import ru.ozon.route256.feature_profile.presentation.view.ProfileFragment
import ru.ozon.route256.feature_profile_api.ProfileNavigationApi
import javax.inject.Inject

class ProfileNavigationImpl @Inject constructor(): ProfileNavigationApi {

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment is PDPFragment) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProfileFragment.TAG) == null
        } else {
            true
        }
    }
}