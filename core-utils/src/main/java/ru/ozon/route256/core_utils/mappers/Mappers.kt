package ru.ozon.route256.core_utils.mappers

import ru.ozon.route256.core_local_storage_api.models.Product
import ru.ozon.route256.core_local_storage_api.models.ProductInList
import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.core_network_api.models.ProductInListDTO

fun Product.toProductDTO(): ProductDTO {
    return ProductDTO(
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

fun ProductDTO.toProduct(): Product {
    return Product(
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

fun ProductInListDTO.toProductInList(): ProductInList {
    return ProductInList(
        guid,
        image,
        name,
        price,
        rating,
        isFavorite,
        isInCart,
        0
    )
}

fun ProductInList.toProductInListDTO(): ProductInListDTO {
    return ProductInListDTO(
        guid,
        image,
        name,
        price,
        rating,
        isFavorite,
        isInCart
    )
}