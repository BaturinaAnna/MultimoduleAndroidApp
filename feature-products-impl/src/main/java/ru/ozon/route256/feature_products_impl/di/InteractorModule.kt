package ru.ozon.route256.feature_products_impl.di

import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindProductsInteractor(interactorImpl: ProductsInteractorImpl): ProductsInteractor
}