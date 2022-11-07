package ru.ozon.route256.feature_new_product_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_new_product_impl.data.repository_impl.NewProductRepositoryImpl
import ru.ozon.route256.feature_new_product_impl.domain.repository.NewProductRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindNewProductRepository(repository: NewProductRepositoryImpl): NewProductRepository
}