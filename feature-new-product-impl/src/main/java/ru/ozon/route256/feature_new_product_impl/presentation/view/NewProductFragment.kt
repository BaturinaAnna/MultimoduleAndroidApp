package ru.ozon.route256.feature_new_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_new_product_impl.databinding.NewProductFragmentBinding
import ru.ozon.route256.feature_new_product_api.NewProductNavigationApi
import ru.ozon.route256.feature_new_product_impl.di.NewProductFeatureComponent
import ru.ozon.route256.feature_new_product_impl.presentation.view_models.NewProductViewModel
import javax.inject.Inject

class NewProductFragment : Fragment() {
    companion object{
        const val TAG = "NewProductFragment"
    }

    private lateinit var binding: NewProductFragmentBinding

    @Inject
    lateinit var newProductNavigationApi: NewProductNavigationApi

    @Inject
    lateinit var vm: NewProductViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        NewProductFeatureComponent.newProductFeatureComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewProductFragmentBinding.inflate(inflater)
        initListeners()
        return binding.root
    }

    override fun onPause() {
        if (isRemoving) {
            if (newProductNavigationApi.isFeatureClosed(this)) {
                NewProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun initListeners() {
        binding.addNewProductButton.setOnClickListener {
            vm.addNewProduct(
                name = binding.newProductNameET.text.toString(),
                price = binding.newProductPriceET.text.toString(),
                description = binding.newProductDescriptionET.text.toString()
            )
            activity?.onBackPressed()
        }
    }
}