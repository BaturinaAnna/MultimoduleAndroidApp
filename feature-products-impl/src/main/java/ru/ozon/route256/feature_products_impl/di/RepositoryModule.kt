package ru.ozon.route256.feature_products_impl.di

import ru.ozon.route256.feature_products_impl.data.repository_impl.ProductsRepositoryImpl
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}