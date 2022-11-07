package ru.ozon.route256.feature_profile.di

import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.feature_profile_api.ProfileNavigationApi

interface ProfileFeatureDependencies {
    fun productNavigationApi(): ProfileNavigationApi
    fun localDatabaseApi(): LocalDatabaseApi
}