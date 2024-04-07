package com.thucthantinh.createpassword

import android.app.Application
import com.thucthantinh.createpassword.di.dataSourceModule
import com.thucthantinh.createpassword.di.dispatcherModule
import com.thucthantinh.createpassword.di.repositoryModule
import com.thucthantinh.createpassword.di.useCaseModule
import com.thucthantinh.createpassword.di.viewModelModule
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