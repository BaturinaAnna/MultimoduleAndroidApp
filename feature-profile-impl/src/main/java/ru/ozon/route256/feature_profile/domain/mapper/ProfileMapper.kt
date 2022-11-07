package ru.ozon.route256.feature_profile.domain.mapper

import ru.ozon.route256.core_local_storage_api.models.Profile
import ru.ozon.route256.feature_profile.presentation.view_objects.Gender
import ru.ozon.route256.feature_profile.presentation.view_objects.ProfileVO
import ru.ozon.route256.feature_profile.presentation.view_objects.Region

fun Profile.toProfileVO(): ProfileVO {
    return ProfileVO(
        name = name,
        age = age,
        gender = if(gender != null) Gender.values()[gender!!] else null,
        region = if(region != null) Region.values()[region!!] else null
    )
}

fun ProfileVO.toProfile(): Profile {
    return Profile(
        name = name,
        age = age,
        gender = Gender.values().indexOf(gender),
        region = Region.values().indexOf(region)
    )
}