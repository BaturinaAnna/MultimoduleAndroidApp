package ru.ozon.route256.feature_pdp_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_impl.domain.interactor.PDPInteractor
import ru.ozon.route256.feature_pdp_impl.domain.interactor.PDPInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindPdpInteractor(interactor: PDPInteractorImpl): PDPInteractor
}