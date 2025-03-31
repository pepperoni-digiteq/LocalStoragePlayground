package com.digiteqautomotive.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digiteqautomotive.myapplication.data.datastore.UserPreferencesDataStore
import com.digiteqautomotive.myapplication.domain.models.UserModel
import com.digiteqautomotive.myapplication.domain.repository.UserRepository
import com.digiteqautomotive.myapplication.security.SecurityUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository,
                    private val userPreferencesDataStore: UserPreferencesDataStore) : ViewModel() {

    val allUsers: Flow<List<UserModel>> = userRepository.getAllUsers()
    val usernameFlow: Flow<String?> = userPreferencesDataStore.usernameFlow

    fun registerUser(name: String, password: String) {
        viewModelScope.launch {
            val salt = SecurityUtils.generateSalt()
            val hashedPassword = SecurityUtils.hashPassword(password, salt)
            userRepository.saveUser(UserModel(name = name, passwordHash = hashedPassword))
        }
    }

    fun saveUserPreferences(username: String, loggedIn: Boolean) {
        viewModelScope.launch {
            userPreferencesDataStore.saveUserData(username, loggedIn)
        }
    }
}