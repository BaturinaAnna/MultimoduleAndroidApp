package ru.ozon.route256.core_local_storage_impl.data

import ru.ozon.route256.core_local_storage_api.LocalDatabaseApi
import ru.ozon.route256.core_local_storage_api.di.LocalStorageComponent
import ru.ozon.route256.core_local_storage_api.models.Product
import ru.ozon.route256.core_local_storage_api.models.ProductInList
import ru.ozon.route256.core_local_storage_api.models.Profile
import ru.ozon.route256.core_local_storage_api.models.ViewCountersTuple
import javax.inject.Inject

class LocalDatabaseApiImpl @Inject constructor() : LocalDatabaseApi {
    override fun getProducts(): List<Product> {
        return LocalStorageComponent.database.productDao().getProducts()
    }

    override fun saveProducts(products: List<Product>) {
        return LocalStorageComponent.database.productDao().saveProducts(products)
    }

    override fun getProductsInList(): List<ProductInList> {
        return LocalStorageComponent.database.productInListDao().getProductsInList()
    }

    override fun saveProductsInList(products: List<ProductInList>) {
        LocalStorageComponent.database.productInListDao().saveProductsInList(products)
    }

    override fun getViewsCounters(): List<ViewCountersTuple> {
        return LocalStorageComponent.database.productInListDao().getViewsCounters()
    }

    override fun updateViewsCounterForProduct(guid: String) {
        LocalStorageComponent.database.productInListDao().updateViewsCounterForProduct(guid)
    }

    override fun addToCart(guid: String) {
        LocalStorageComponent.database.productInListDao().addToCart(guid)
    }

    override fun getCart(): List<String> {
        return LocalStorageComponent.database.productInListDao().getCart()
    }

    override fun getPersonProfile(): Profile? {
        return LocalStorageComponent.database.profileDao().getPersonProfile()
    }

    override fun savePersonProfile(profile: Profile) {
        LocalStorageComponent.database.profileDao().savePersonProfile(profile)
    }
}