package ru.ozon.route256.core_local_storage_api.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ozon.route256.core_local_storage_api.models.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveProducts(products: List<Product>)
}