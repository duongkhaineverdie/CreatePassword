package com.thucthantinh.createpassword.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import com.thucthantinh.createpassword.utils.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "create_password")

class DataStoreManager(private val context: Context) {
    init {
        context
    }

    companion object {
        val PASSWORD_CREATED = stringSetPreferencesKey("PASSWORD_CREATED")
    }

    suspend fun savePasswords(passwords: Set<String>) {
        context.dataStore.edit {
            it[PASSWORD_CREATED] = passwords
        }
    }

    val passwords: Flow<Set<String>> = context.dataStore.data.map {
        it[PASSWORD_CREATED] ?: emptySet()
    }
}