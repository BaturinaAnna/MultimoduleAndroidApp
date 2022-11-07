package ru.ozon.route256.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.feature_pdp_impl.R
import com.example.feature_pdp_impl.databinding.PdpFragmentBinding
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.di.PDPFeatureComponent
import ru.ozon.route256.feature_pdp_impl.presentation.view.adapters.ProductImagesAdapter
import ru.ozon.route256.feature_pdp_impl.presentation.view_models.PDPViewModel
import ru.ozon.route256.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PDPFragment : Fragment() {
    companion object {
        const val TAG = "PDPFragment"
    }

    private lateinit var binding: PdpFragmentBinding

    @Inject
    lateinit var pdpNavigationApi: PDPNavigationApi

    @Inject
    lateinit var vm: PDPViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PDPFeatureComponent.pdpFeatureComponent?.inject(this)
    }

    override fun onPause() {
        if (isRemoving) {
            if (pdpNavigationApi.isFeatureClosed(this)) {
                PDPFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PdpFragmentBinding.inflate(inflater)
        val productId = arguments?.get("product_id") as String
        vm.getProductById(productId)
        vm.productLD.observe(viewLifecycleOwner) {
            initView(it)
            vm.updateViewsCounterForProduct(productId)
        }
        return binding.root
    }

    private fun initView(product: ProductVO) {
        val adapter = ProductImagesAdapter(product.images)
        binding.productImageRV.adapter = adapter
        binding.nameTV.text = product.name
        binding.priceTV.text = product.price
        binding.descriptionTV.text = product.description
        binding.ratingView.rating = product.rating.toFloat()
    }
}