package ru.ozon.route256.core_local_storage_api.di

import android.content.Context
import androidx.room.Room
import dagger.Component
import dagger.internal.Preconditions
import ru.ozon.route256.core_local_storage_api.LocalDatabase

@Component(dependencies = [Context::class])
abstract class LocalStorageComponent {
    companion object {
        @Volatile
        var instance: LocalStorageComponent? = null

        @Volatile
        lateinit var database: LocalDatabase


        fun initAndGet(context: Context): LocalStorageComponent? {
            if (instance == null) {
                synchronized(LocalStorageComponent::class) {
                    database = Room.databaseBuilder(
                        context, LocalDatabase::class.java, "local-database"
                    ).build()
                    instance = DaggerLocalStorageComponent.builder()
                        .context(context)
                        .build()
                }
            }
            return instance
        }

        fun get(): LocalStorageComponent {
            return Preconditions.checkNotNull(instance,
                "LocalStorageComponent is not initialized yet. Call init first.")!!
        }
    }
}