package com.emenike.createpassword.di

import com.emenike.createpassword.data.datastore.DataStoreManager
import com.emenike.createpassword.data.repository.IRepository
import com.emenike.createpassword.domain.interactors.GetPasswordsUseCase
import com.emenike.createpassword.domain.interactors.SavePasswordsUseCase
import com.emenike.createpassword.domain.repository.RepositoryImpl
import com.emenike.createpassword.presentation.MainViewModel
import com.emenike.createpassword.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel() }
    single { HomeViewModel(get(), get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val dataSourceModule = module {
    single { DataStoreManager(get()) }
}

val useCaseModule = module {
    factory { GetPasswordsUseCase(get(), get()) }
    factory { SavePasswordsUseCase(get(), get()) }
}

val repositoryModule = module {
    single<IRepository> { RepositoryImpl(get()) }
}
