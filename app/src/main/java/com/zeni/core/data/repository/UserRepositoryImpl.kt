package com.zeni.core.data.repository

import com.zeni.core.data.database.dao.UserDao
import com.zeni.core.data.mappers.toEntity
import com.zeni.core.domain.model.User
import com.zeni.core.domain.repository.UserRepository
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
): UserRepository {
    override suspend fun addUser(user: User) {
        userDao.upsertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}