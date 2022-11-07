package ru.ozon.route256.feature_products_impl.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.feature_products_impl.R
import com.example.feature_products_impl.databinding.ProductsFragmentBinding
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent
import ru.ozon.route256.feature_products_impl.presentation.view.adapters.ProductsAdapter
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerViewItem
import ru.ozon.route256.feature_products_impl.presentation.view_models.ProductsViewModel
import ru.ozon.route256.feature_products_impl.presentation.view_objects.Gender
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.Region
import javax.inject.Inject

class ProductsFragment : Fragment() {
    companion object {
        const val TAG = "ProductsFragment"
    }

    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var binding: ProductsFragmentBinding
    private lateinit var productsList: List<ProductInListVO>

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    @Inject
    lateinit var vm: ProductsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = ProductsFragmentBinding.inflate(inflater)
        productsAdapter = ProductsAdapter(
            { productInListVO ->
                adapterOnClick(productInListVO)
            },
            { productInListVO ->
                cartButtonOnClick(productInListVO)
            })
        binding.productsRecyclerView.adapter = productsAdapter
        initListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getProfile()
        vm.profileLD.observe(viewLifecycleOwner) { person ->
            if (person?.age != null && person.gender != null && person.region != null) {
                vm.getRecommendation(
                    mutableListOf(
                        person.age,
                        Gender.values().indexOf(person.gender),
                        Region.values().indexOf(person.region)
                    )
                )
            }
        }

        vm.updateProductInList()
        vm.productLD.observe(viewLifecycleOwner) { productsList ->
            this.productsList = productsList
            productsAdapter.submitList(createListForAdapter())
        }

        vm.productRecommendation.observe(viewLifecycleOwner) {
            productsAdapter.submitList(createListForAdapter())
        }
    }

    override fun onResume() {
        super.onResume()
        vm.updateProductInList()
    }

    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profileMenuItem -> {
                productNavigationApi.navigateToProfile(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initListeners() {
        binding.addProductButton.setOnClickListener {
            productNavigationApi.navigateToNewProduct(this)
        }
    }

    private fun adapterOnClick(product: ProductInListVO) {
        productNavigationApi.navigateToPDP(this, product.guid)
    }

    private fun cartButtonOnClick(product: ProductInListVO) {
        vm.addToCart(product)
    }

    private fun createListForAdapter(): List<ProductsRecyclerViewItem> {
        return vm.divideProductsByPrice(
            resources.getString(R.string.rv_header_special_recommendation),
            resources.getString(R.string.rv_header_less_than_100),
            resources.getString(R.string.rv_header_more_than_100),
            productsList
        )
    }
}