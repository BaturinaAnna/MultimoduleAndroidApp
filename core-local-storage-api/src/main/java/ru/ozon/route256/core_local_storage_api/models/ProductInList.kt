package ru.ozon.route256.core_local_storage_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.ozon.route256.core_local_storage_api.converters.Converters

data class ViewCountersTuple(
    @ColumnInfo(name = "guid") val guid: String,
    @ColumnInfo(name = "viewCounter") val viewCounter: Int
)

@TypeConverters(Converters::class)
@Entity
data class ProductInList(
    @PrimaryKey
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    var isInCart: Boolean,
    var viewCounter: Int
)