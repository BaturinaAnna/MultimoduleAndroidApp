package ru.ozon.route256.feature_profile.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_profile.data.repository_impl.ProfileRepositoryImpl
import ru.ozon.route256.feature_profile.domain.repository.ProfileRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository
}