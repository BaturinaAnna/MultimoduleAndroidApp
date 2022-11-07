package ru.ozon.route256.feature_products_impl.domain.mapper

import ru.ozon.route256.core_network_api.models.ProductInListDTO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO

fun ProductInListDTO.toProductInListVO(viewsCounter: Int): ProductInListVO {
    return ProductInListVO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        viewsCnt = viewsCounter
    )
}

fun ProductInListVO.toProductInListDTO(): ProductInListDTO {
    return ProductInListDTO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart
    )
}