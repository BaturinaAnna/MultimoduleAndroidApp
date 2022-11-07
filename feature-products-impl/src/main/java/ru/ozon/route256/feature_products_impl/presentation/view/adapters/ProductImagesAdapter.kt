package ru.ozon.route256.feature_products_impl.presentation.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.presentation.view.view_holders.ProductImageViewHolder

class ProductImagesAdapter(var images: List<String> = emptyList()) :
    RecyclerView.Adapter<ProductImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        return ProductImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }
}