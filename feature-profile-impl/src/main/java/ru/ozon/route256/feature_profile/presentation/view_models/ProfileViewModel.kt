package ru.ozon.route256.feature_profile.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.feature_profile.domain.interactor.ProfileInteractor
import ru.ozon.route256.feature_profile.presentation.view_objects.ProfileVO
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor
) : ViewModel() {

    private val _profileLD: MutableLiveData<ProfileVO> = MutableLiveData()
    val profileLD: LiveData<ProfileVO>
        get() = _profileLD

    fun getProfile() {
        viewModelScope.launch {
            _profileLD.postValue(profileInteractor.getPersonProfile())
        }
    }

    fun saveProfile(profile: ProfileVO) {
        if (_profileLD.value != profile) {
            profileInteractor.savePersonProfile(profile)
        }
    }
}