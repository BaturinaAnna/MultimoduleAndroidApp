package ru.ozon.route256.core_navigation_impl.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core_navigation_impl.R
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor(): ProductNavigationApi {

    override fun navigateToPDP(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI()
        fragment.findNavController().navigate(
            R.id.action_productsFragment_to_pdpFragment,
            bundleOf(Pair("product_id", guid))
        )
    }

    override fun navigateToNewProduct(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureNewProductDI()
        fragment.findNavController().navigate(
            R.id.action_productsFragment_to_newProductFragment
        )
    }

    override fun navigateToProfile(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureProfileDI()
        fragment.findNavController().navigate(
            R.id.action_productsFragment_to_profileFragment
        )
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment is ProductsFragment) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProductsFragment.TAG) == null
        } else {
            true
        }
    }
}