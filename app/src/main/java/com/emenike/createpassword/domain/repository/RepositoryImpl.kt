package com.emenike.createpassword.domain.repository

import com.emenike.createpassword.data.datastore.DataStoreManager
import com.emenike.createpassword.data.repository.IRepository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dataStoreManager: DataStoreManager,
): IRepository {
    override fun getPasswords(): Flow<Set<String>> = dataStoreManager.passwords

    override suspend fun savePasswords(passwords: Set<String>) = dataStoreManager.savePasswords(passwords)
}