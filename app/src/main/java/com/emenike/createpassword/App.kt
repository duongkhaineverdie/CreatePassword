package com.emenike.createpassword

import android.app.Application
import com.emenike.createpassword.di.dataSourceModule
import com.emenike.createpassword.di.dispatcherModule
import com.emenike.createpassword.di.repositoryModule
import com.emenike.createpassword.di.useCaseModule
import com.emenike.createpassword.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                dispatcherModule,
                dataSourceModule,
                useCaseModule,
                repositoryModule,
            )
        }
    }
}