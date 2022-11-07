package ru.ozon.route256.feature_products_impl.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_products_impl.presentation.view_models.ProductsViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel
}