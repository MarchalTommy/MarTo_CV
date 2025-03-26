package com.example.martocv

import android.app.Application
import com.example.martocv.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MarToCvApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin activity - use Level.NONE for production builds
            androidLogger(Level.DEBUG) // or Level.INFO or Level.ERROR
            // Declare Android context
            androidContext(this@MarToCvApplication)
            // Declare modules to use
            modules(appModule)
        }
    }
}