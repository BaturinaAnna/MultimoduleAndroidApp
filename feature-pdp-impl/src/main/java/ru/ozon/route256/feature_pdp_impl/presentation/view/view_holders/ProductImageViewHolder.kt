package ru.ozon.route256.feature_pdp_impl.presentation.view.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_pdp_impl.R
import com.example.feature_pdp_impl.databinding.PdpImageItemBinding

class ProductImageViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private var binding: PdpImageItemBinding = PdpImageItemBinding.bind(itemView)

    fun bind(imageUrl: String) {
        Glide.with(itemView)
            .load(imageUrl)
            .error(R.drawable.ic_default_food)
            .into(binding.productIV)
    }
}
