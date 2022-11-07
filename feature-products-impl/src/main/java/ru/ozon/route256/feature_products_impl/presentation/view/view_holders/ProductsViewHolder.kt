package ru.ozon.route256.feature_products_impl.presentation.view.view_holders

import android.view.View
import com.bumptech.glide.Glide
import com.example.feature_products_impl.R
import com.example.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view.adapters.ProductImagesAdapter
import ru.ozon.route256.feature_products_impl.presentation.view.custom_views.CartButton
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.items.ProductsRecyclerViewItem
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInListVO

class ProductsViewHolder(
    itemView: View,
    onClick: (ProductInListVO) -> Unit,
    val onClickCartButton: (ProductInListVO) -> Unit
) : BaseViewHolder(itemView) {
    private lateinit var currentProduct: ProductInListVO
    private var binding: ProductListItemBinding

    init {
        binding = ProductListItemBinding.bind(itemView)
        itemView.setOnClickListener {
            onClick(currentProduct)
        }
    }

    override fun bind(productsRecyclerViewItem: ProductsRecyclerViewItem) {
        currentProduct = (productsRecyclerViewItem as ProductsRecyclerViewItem.ProductItem).product
        binding.productImageRV.adapter = ProductImagesAdapter(currentProduct.image)
        binding.nameTV.text = currentProduct.name
        binding.priceTV.text = currentProduct.price
        binding.ratingView.rating = currentProduct.rating.toFloat()
        binding.viewsCounterTV.text = currentProduct.viewsCnt.toString()
        binding.cartButton.initButtonState(currentProduct.isInCart)
        binding.cartButton.setOnClickListener {
            onClickCartButton(currentProduct)
            binding.cartButton.productAddedToCart()
        }
    }
}