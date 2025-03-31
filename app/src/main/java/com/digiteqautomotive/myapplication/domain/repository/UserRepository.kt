package com.digiteqautomotive.myapplication.domain.repository

import com.digiteqautomotive.myapplication.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(name: String): UserModel?
    fun getAllUsers(): Flow<List<UserModel>>
    suspend fun saveUser(user: UserModel)
}