package com.shahin.mlkit

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.shahin.mlkit.di.appModule
import com.shahin.mlkit.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                    appModule,
                    repositoryModule
            )
        }

    }

}