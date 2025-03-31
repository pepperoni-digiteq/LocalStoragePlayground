package com.digiteqautomotive.myapplication.data.repository

import com.digiteqautomotive.myapplication.data.database.User
import com.digiteqautomotive.myapplication.data.database.UserDao
import com.digiteqautomotive.myapplication.domain.models.UserModel
import com.digiteqautomotive.myapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {
    override suspend fun getUser(name: String): UserModel? {
        val entity = userDao.getUserByName(name) ?: return null
        return UserModel(entity.name, entity.passwordHash)
    }

    override fun getAllUsers(): Flow<List<UserModel>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { entity ->
                UserModel(entity.name, entity.passwordHash)
            }
        }
    }

    override suspend fun saveUser(user: UserModel) {
        val entity = User(name = user.name, passwordHash = user.passwordHash)
        userDao.insertUser(entity)
    }
}