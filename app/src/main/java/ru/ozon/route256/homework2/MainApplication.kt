package ru.ozon.route256.homework2

import android.app.Application
import android.content.Context
import androidx.work.WorkManager

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        workManager = WorkManager.getInstance(this)
        context = applicationContext
    }

    companion object {
        @Volatile
        lateinit var workManager: WorkManager
            private set

        @Volatile
        lateinit var context: Context
            private set
    }
}
