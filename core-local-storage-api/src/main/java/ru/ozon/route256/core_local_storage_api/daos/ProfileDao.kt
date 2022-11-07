package ru.ozon.route256.core_local_storage_api.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ozon.route256.core_local_storage_api.models.Profile

@Dao
interface ProfileDao {

    @Query("SELECT * FROM Profile")
    fun getPersonProfile(): Profile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePersonProfile(profile: Profile)
}