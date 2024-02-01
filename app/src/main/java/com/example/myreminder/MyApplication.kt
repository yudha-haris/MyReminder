package com.example.myreminder

import android.app.Application
import com.example.myreminder.core.di.databaseModule
import com.example.myreminder.core.di.networkModule
import com.example.myreminder.core.di.repositoryModule
import com.example.myreminder.di.useCaseModule
import com.example.myreminder.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}