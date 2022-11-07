package ru.ozon.route256.feature_products_impl.presentation.view.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerViewItem

abstract class BaseViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(productsRecyclerViewItem: ProductsRecyclerViewItem)
}