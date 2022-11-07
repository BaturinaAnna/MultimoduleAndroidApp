package ru.ozon.route256.feature_pdp_impl.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_impl.presentation.view_models.PDPViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindPDPViewModel(viewModel: PDPViewModel): ViewModel
}