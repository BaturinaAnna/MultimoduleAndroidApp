package ru.ozon.route256.feature_pdp_impl.domain.mapper

import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.feature_pdp_impl.presentation.view_objects.ProductVO

fun ProductDTO.toProductVO(): ProductVO {
    return ProductVO(
        guid,
        name,
        price,
        description,
        rating,
        isFavorite,
        isInCart,
        images,
        weight,
        count,
        availableCount,
        additionalParams
    )
}