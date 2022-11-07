package ru.ozon.route256.core_local_storage_api

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ozon.route256.core_local_storage_api.daos.ProductDao
import ru.ozon.route256.core_local_storage_api.daos.ProductInListDao
import ru.ozon.route256.core_local_storage_api.daos.ProfileDao
import ru.ozon.route256.core_local_storage_api.models.Product
import ru.ozon.route256.core_local_storage_api.models.ProductInList
import ru.ozon.route256.core_local_storage_api.models.Profile

@Database(entities = [Product::class, ProductInList::class, Profile::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun productInListDao(): ProductInListDao
    abstract fun profileDao(): ProfileDao
}