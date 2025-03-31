package com.digiteqautomotive.myapplication.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferencesDataStore(private val context: Context) {

    companion object {
        val USERNAME_KEY = stringPreferencesKey("username")
        val LOGGED_IN_KEY = booleanPreferencesKey("logged_in")
    }

    val usernameFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[USERNAME_KEY] }

    val loggedInFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[LOGGED_IN_KEY] ?: false }

    suspend fun saveUserData(username: String, loggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[LOGGED_IN_KEY] = loggedIn
        }
    }
}