package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import javax.inject.Inject

class PDPNavigationImpl @Inject constructor(): PDPNavigationApi {

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment is PDPFragment) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(PDPFragment.TAG) == null
        } else {
            true
        }
    }
}