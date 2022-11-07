package ru.ozon.route256.core_local_storage_api.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (
    val name: String?,
    val age: Int?,
    val gender: Int?,
    val region: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}