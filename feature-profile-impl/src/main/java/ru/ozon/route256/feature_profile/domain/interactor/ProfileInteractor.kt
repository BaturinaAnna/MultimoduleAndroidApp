package ru.ozon.route256.feature_profile.domain.interactor

import ru.ozon.route256.feature_profile.presentation.view_objects.ProfileVO

interface ProfileInteractor {
    fun savePersonProfile(profile: ProfileVO)
    suspend fun getPersonProfile(): ProfileVO?
}