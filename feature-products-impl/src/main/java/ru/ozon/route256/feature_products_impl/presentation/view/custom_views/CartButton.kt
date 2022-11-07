package ru.ozon.route256.feature_products_impl.presentation.view.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.feature_products_impl.R


class CartButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var state: CartButtonState
    private var cartButton: TextView
    private var progressBar: ProgressBar

    init {
        View.inflate(context, R.layout.cart_button, this).apply {
            cartButton = findViewById(R.id.cartButtonText)
            progressBar = findViewById(R.id.progressBar)
        }
        state = CartButtonState.NOT_IN_CART
        dispatch()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (state == CartButtonState.NOT_IN_CART) {
            state = CartButtonState.IN_PROGRESS
            dispatch()
        }
        return super.onTouchEvent(event)
    }

    fun initButtonState(isInCart: Boolean) {
        state = if (isInCart) {
            CartButtonState.IN_CART
        } else {
            CartButtonState.NOT_IN_CART
        }
        dispatch()
    }

    fun productAddedToCart() {
        state = CartButtonState.IN_CART
        dispatch()
    }

    private fun dispatch() {
        when (state) {
            CartButtonState.NOT_IN_CART -> {
                cartButton.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                cartButton.text =
                    resources.getString(R.string.cart_button_title_not_in_cart)
                cartButton.setBackgroundResource(
                    com.google.android.material.R.color.design_default_color_primary
                )
            }
            CartButtonState.IN_PROGRESS -> {
                cartButton.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            CartButtonState.IN_CART -> {
                cartButton.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                cartButton.text =
                    resources.getString(R.string.cart_button_title_in_cart)
                cartButton.setBackgroundResource(
                    com.google.android.material.R.color.design_default_color_secondary
                )
            }
        }
    }

    enum class CartButtonState {
        NOT_IN_CART,
        IN_PROGRESS,
        IN_CART
    }
}