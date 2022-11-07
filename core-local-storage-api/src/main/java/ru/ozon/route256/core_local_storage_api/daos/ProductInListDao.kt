package ru.ozon.route256.core_local_storage_api.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ozon.route256.core_local_storage_api.models.ProductInList
import ru.ozon.route256.core_local_storage_api.models.ViewCountersTuple

@Dao
interface ProductInListDao {

    @Query("SELECT * FROM ProductInList")
    fun getProductsInList(): List<ProductInList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveProductsInList(products: List<ProductInList>)

    @Query("SELECT guid, viewCounter FROM ProductInList")
    fun getViewsCounters(): List<ViewCountersTuple>

    @Query("UPDATE ProductInList SET viewCounter = viewCounter + 1 WHERE guid = :guid")
    fun updateViewsCounterForProduct(guid: String)

    @Query("UPDATE ProductInList SET isInCart = 1 WHERE guid = :guid")
    fun addToCart(guid: String)

    @Query("SELECT guid FROM ProductInList WHERE isInCart = 1")
    fun getCart(): List<String>
}