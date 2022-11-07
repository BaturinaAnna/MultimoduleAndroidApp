package ru.ozon.route256.feature_profile.domain.repository

import ru.ozon.route256.core_local_storage_api.models.Profile

interface ProfileRepository {
    fun savePersonProfile(profile: Profile)
    suspend fun getPersonProfile(): Profile?
}