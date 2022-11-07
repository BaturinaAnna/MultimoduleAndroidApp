package ru.ozon.route256.feature_new_product_impl.domain.mapper

import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.feature_new_product_impl.presentation.view_objects.ProductVO

fun ProductVO.toProductDTO(): ProductDTO =
    ProductDTO(
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