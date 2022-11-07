package ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items

import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO

sealed class ProductsRecyclerViewItem {
    class HeaderItem(val title: String) : ProductsRecyclerViewItem()
    class ProductItem(val product: ProductInListVO) : ProductsRecyclerViewItem()
}