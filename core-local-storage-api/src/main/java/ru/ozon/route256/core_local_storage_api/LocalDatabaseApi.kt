package ru.ozon.route256.core_local_storage_api

import ru.ozon.route256.core_local_storage_api.models.Product
import ru.ozon.route256.core_local_storage_api.models.ProductInList
import ru.ozon.route256.core_local_storage_api.models.Profile
import ru.ozon.route256.core_local_storage_api.models.ViewCountersTuple

interface LocalDatabaseApi {
    fun getProducts(): List<Product>
    fun saveProducts(products: List<Product>)
    fun getProductsInList(): List<ProductInList>
    fun saveProductsInList(products: List<ProductInList>)
    fun getViewsCounters(): List<ViewCountersTuple>
    fun updateViewsCounterForProduct(guid: String)
    fun addToCart(guid: String)
    fun getCart(): List<String>
    fun getPersonProfile(): Profile?
    fun savePersonProfile(profile: Profile)
}