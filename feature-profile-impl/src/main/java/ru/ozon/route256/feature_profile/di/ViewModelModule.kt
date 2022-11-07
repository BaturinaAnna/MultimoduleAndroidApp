package ru.ozon.route256.feature_profile.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_profile.presentation.view_models.ProfileViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}