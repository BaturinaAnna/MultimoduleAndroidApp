package ru.ozon.route256.feature_products_impl.presentation.view.view_holders

import android.view.View
import com.example.feature_products_impl.databinding.HeaderListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerViewItem

class HeaderViewHolder(
    itemView: View,
) : BaseViewHolder(itemView) {
    private lateinit var currentTitle: String
    private var binding: HeaderListItemBinding

    init {
        binding = HeaderListItemBinding.bind(itemView)
    }

    override fun bind(productsRecyclerViewItem: ProductsRecyclerViewItem) {
        currentTitle = (productsRecyclerViewItem as ProductsRecyclerViewItem.HeaderItem).title
        binding.headerTitleTV.text = currentTitle
    }
}