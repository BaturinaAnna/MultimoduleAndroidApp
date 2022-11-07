package ru.ozon.route256.feature_new_product_impl.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_new_product_impl.presentation.view_models.NewProductViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindNewProductViewModel(viewModel: NewProductViewModel): ViewModel
}