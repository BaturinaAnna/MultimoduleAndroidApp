package ru.ozon.route256.feature_profile.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_profile.domain.interactor.ProfileInteractor
import ru.ozon.route256.feature_profile.domain.interactor.ProfileInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindsProfileInteractor(interactorImpl: ProfileInteractorImpl): ProfileInteractor
}