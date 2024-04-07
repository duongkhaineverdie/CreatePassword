package com.thucthantinh.createpassword.di

import com.thucthantinh.createpassword.data.datastore.DataStoreManager
import com.thucthantinh.createpassword.data.repository.IRepository
import com.thucthantinh.createpassword.domain.interactors.GetPasswordsUseCase
import com.thucthantinh.createpassword.domain.interactors.SavePasswordsUseCase
import com.thucthantinh.createpassword.domain.repository.RepositoryImpl
import com.thucthantinh.createpassword.presentation.MainViewModel
import com.thucthantinh.createpassword.presentation.ui.home.HomeViewModel
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
