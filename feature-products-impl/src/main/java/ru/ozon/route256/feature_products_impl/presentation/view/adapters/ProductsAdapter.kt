package ru.ozon.route256.feature_products_impl.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.BaseViewHolder
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.HeaderViewHolder
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.ProductsViewHolder
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerItemViewType
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerViewItem
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO

class ProductsAdapter(
    private val onClick: (ProductInListVO) -> Unit,
    private val onClickCartButton: (ProductInListVO) -> Unit
) : ListAdapter<ProductsRecyclerViewItem, BaseViewHolder>(ProductDiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ProductsRecyclerViewItem.ProductItem -> ProductsRecyclerItemViewType.PRODUCT.value
            is ProductsRecyclerViewItem.HeaderItem -> ProductsRecyclerItemViewType.HEADER.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            ProductsRecyclerItemViewType.PRODUCT.value -> {
                val view: View =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_list_item, parent, false)
                ProductsViewHolder(view, onClick, onClickCartButton)
            }
            ProductsRecyclerItemViewType.HEADER.value -> {
                val view: View =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.header_list_item, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                error("No such type of view")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object ProductDiffCallback : DiffUtil.ItemCallback<ProductsRecyclerViewItem>() {
    override fun areItemsTheSame(
        oldItem: ProductsRecyclerViewItem,
        newItem: ProductsRecyclerViewItem
    ): Boolean {
        return if (oldItem is ProductsRecyclerViewItem.ProductItem &&
            newItem is ProductsRecyclerViewItem.ProductItem
        ) {
            oldItem.product.guid == newItem.product.guid
        } else if (oldItem is ProductsRecyclerViewItem.HeaderItem
            && newItem is ProductsRecyclerViewItem.HeaderItem
        ) {
            oldItem.title == newItem.title
        } else {
            false
        }
    }

    override fun areContentsTheSame(
        oldItem: ProductsRecyclerViewItem,
        newItem: ProductsRecyclerViewItem
    ): Boolean {
        return if (oldItem is ProductsRecyclerViewItem.ProductItem &&
            newItem is ProductsRecyclerViewItem.ProductItem
        ) {
            (oldItem.product.guid == newItem.product.guid) &&
                    (oldItem.product.image == newItem.product.image) &&
                    (oldItem.product.name == newItem.product.name) &&
                    (oldItem.product.price == newItem.product.price) &&
                    (oldItem.product.rating.toFloat() == newItem.product.rating.toFloat()) &&
                    (oldItem.product.viewsCnt == newItem.product.viewsCnt)
        } else if (oldItem is ProductsRecyclerViewItem.HeaderItem
            && newItem is ProductsRecyclerViewItem.HeaderItem
        ) {
            oldItem.title == newItem.title
        } else {
            false
        }
    }
}