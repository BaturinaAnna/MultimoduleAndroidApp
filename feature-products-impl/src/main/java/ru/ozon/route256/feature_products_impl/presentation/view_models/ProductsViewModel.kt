package ru.ozon.route256.feature_products_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerViewItem
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProfileVO
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor
) : ViewModel() {
    private val _productLD: MutableLiveData<List<ProductInListVO>> = MutableLiveData(emptyList())
    val productLD: LiveData<List<ProductInListVO>>
        get() = _productLD

    private val _productRecommendation: MutableLiveData<String> = MutableLiveData()
    val productRecommendation: LiveData<String>
        get() = _productRecommendation

    private val _profileLD: MutableLiveData<ProfileVO> = MutableLiveData()
    val profileLD: LiveData<ProfileVO>
        get() = _profileLD

    fun updateProductInList() {
        viewModelScope.launch {
            _productLD.postValue(productsInteractor.getProducts())
        }
    }

    fun addToCart(product: ProductInListVO) {
        productsInteractor.addToCart(product)
    }

    fun getRecommendation(inputParams: List<Int>) {
        productsInteractor.getRecommendedProduct(inputParams)
            .observeForever { recommendation ->
                _productRecommendation.postValue(recommendation)
            }
    }

    fun getProfile() {
        viewModelScope.launch {
            _profileLD.postValue(productsInteractor.getProfile())
        }
    }

    fun divideProductsByPrice(
        headerSpecialRecommendation: String,
        headerTitleLessThan100: String,
        headerTitleMoreThan100: String,
        products: List<ProductInListVO>
    ): List<ProductsRecyclerViewItem> {
        val productsRecyclerViewItems = mutableListOf<ProductsRecyclerViewItem>()

        productRecommendation.value?.let { recommendation ->
            val recommendedProduct = products.find { it.guid == recommendation }
            recommendedProduct?.let {
                productsRecyclerViewItems.add(
                    ProductsRecyclerViewItem.HeaderItem(
                        headerSpecialRecommendation
                    )
                )
                productsRecyclerViewItems.add(
                    ProductsRecyclerViewItem.ProductItem(
                        recommendedProduct
                    )
                )
            }
        }

        productsRecyclerViewItems.add(ProductsRecyclerViewItem.HeaderItem(headerTitleLessThan100))
        val productsPartition = products.partition {
            it.price.toInt() <= 100
        }
        productsPartition.first.map {
            productsRecyclerViewItems.add(ProductsRecyclerViewItem.ProductItem(it))
        }
        productsRecyclerViewItems.add(ProductsRecyclerViewItem.HeaderItem(headerTitleMoreThan100))
        productsPartition.second.map {
            productsRecyclerViewItems.add(ProductsRecyclerViewItem.ProductItem(it))
        }

        return productsRecyclerViewItems
    }
}