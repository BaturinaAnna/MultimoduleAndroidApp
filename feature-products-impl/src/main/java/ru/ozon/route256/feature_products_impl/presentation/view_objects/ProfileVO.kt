package ru.ozon.route256.feature_products_impl.presentation.view_objects

enum class Gender {
    FEMALE, MALE
}

enum class Region {
    CENTRAL,
    SEVERO_ZAPADNY,
    PRIVOLZHSKY,
    SOUTHERN,
    NORTH_CAUCASIAN,
    URAL,
    SIBERIAN,
    FAR_EASTERN
}

data class ProfileVO(
    val name: String?,
    val age: Int?,
    val gender: Gender?,
    val region: Region?
)