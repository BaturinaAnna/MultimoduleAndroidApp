package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.feature_new_product_api.NewProductNavigationApi
import ru.ozon.route256.feature_new_product_impl.presentation.view.NewProductFragment
import javax.inject.Inject

class NewProductNavigationImpl @Inject constructor() : NewProductNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment is NewProductFragment) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(NewProductFragment.TAG) == null
        } else {
            true
        }
    }
}