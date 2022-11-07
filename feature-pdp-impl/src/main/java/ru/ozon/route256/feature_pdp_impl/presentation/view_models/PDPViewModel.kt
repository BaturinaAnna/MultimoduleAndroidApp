package ru.ozon.route256.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.feature_pdp_impl.domain.interactor.PDPInteractor
import ru.ozon.route256.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PDPViewModel @Inject constructor(
    private val pdpInteractor: PDPInteractor
) : ViewModel() {

    private val _productLD: MutableLiveData<ProductVO> = MutableLiveData()
    val productLD: LiveData<ProductVO>
        get() = _productLD

    fun getProductById(guid: String) {
        viewModelScope.launch {
            _productLD.postValue(pdpInteractor.getProductById(guid))
        }
    }

    fun updateViewsCounterForProduct(guid: String) {
        pdpInteractor.updateViewsCounterForProduct(guid)
    }
}