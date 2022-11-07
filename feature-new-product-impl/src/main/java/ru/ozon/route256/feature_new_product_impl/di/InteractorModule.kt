package ru.ozon.route256.feature_new_product_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_new_product_impl.domain.interactor.NewProductInteractor
import ru.ozon.route256.feature_new_product_impl.domain.interactor.NewProductInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindNewProductInteractor(interactorImpl: NewProductInteractorImpl): NewProductInteractor
}