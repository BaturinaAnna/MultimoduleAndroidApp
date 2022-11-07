package ru.ozon.route256.feature_pdp_impl.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.feature_pdp_impl.domain.mapper.toProductVO
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository
import ru.ozon.route256.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PDPInteractorImpl @Inject constructor(
    private val pdpRepository: PDPRepository
) : PDPInteractor {
    override suspend fun getProductById(guid: String): ProductVO? =
        withContext(Dispatchers.IO) {
            pdpRepository.getProductById(guid)?.toProductVO()
        }

    override fun updateViewsCounterForProduct(guid: String) {
        pdpRepository.updateViewsCounterForProduct(guid)
    }
}