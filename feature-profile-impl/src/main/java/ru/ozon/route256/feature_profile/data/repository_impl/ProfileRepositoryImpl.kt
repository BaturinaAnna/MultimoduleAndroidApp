package ru.ozon.route256.feature_profile.data.repository_impl

import kotlinx.coroutines.*
import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_local_storage_api.models.Profile
import ru.ozon.route256.feature_profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDatabaseApi: LocalDatabaseApi
) : ProfileRepository {

    override fun savePersonProfile(profile: Profile) {
        CoroutineScope(Dispatchers.IO).launch {
            localDatabaseApi.savePersonProfile(profile)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getPersonProfile(): Profile? =
        suspendCancellableCoroutine { continuation ->
            continuation.resume(localDatabaseApi.getPersonProfile(), null)
        }
}