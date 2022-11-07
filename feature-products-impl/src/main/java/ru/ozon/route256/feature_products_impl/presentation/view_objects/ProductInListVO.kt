package ru.ozon.route256.feature_products_impl.presentation.view_objects

data class ProductInListVO(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    var viewsCnt: Int
)
