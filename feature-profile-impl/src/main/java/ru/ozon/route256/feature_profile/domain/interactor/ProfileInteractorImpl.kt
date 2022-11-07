package ru.ozon.route256.feature_profile.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.feature_profile.domain.mapper.toProfile
import ru.ozon.route256.feature_profile.domain.mapper.toProfileVO
import ru.ozon.route256.feature_profile.domain.repository.ProfileRepository
import ru.ozon.route256.feature_profile.presentation.view_objects.ProfileVO
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : ProfileInteractor {

    override fun savePersonProfile(profile: ProfileVO) {
        profileRepository.savePersonProfile(profile.toProfile())
    }

    override suspend fun getPersonProfile(): ProfileVO? =
        withContext(Dispatchers.IO) {
            profileRepository.getPersonProfile()?.toProfileVO()
        }
}