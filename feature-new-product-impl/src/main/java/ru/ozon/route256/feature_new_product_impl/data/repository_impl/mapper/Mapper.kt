package ru.ozon.route256.feature_new_product_impl.data.repository_impl.mapper

import ru.ozon.route256.core_local_storage_api.models.ProductInList
import ru.ozon.route256.core_network_api.models.ProductDTO
import ru.ozon.route256.core_network_api.models.ProductInListDTO

fun ProductDTO.toProductInList(): ProductInList =
    ProductInList(
        guid,
        images,
        name,
        price,
        rating,
        isFavorite,
        isInCart,
        0
    )
