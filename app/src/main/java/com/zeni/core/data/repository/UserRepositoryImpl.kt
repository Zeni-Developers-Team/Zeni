package com.zeni.core.data.repository

import com.zeni.core.data.database.dao.UserDao
import com.zeni.core.data.mappers.toDomain
import com.zeni.core.data.mappers.toEntity
import com.zeni.core.domain.model.User
import com.zeni.core.domain.repository.UserRepository
import com.zeni.core.domain.utils.Authenticator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val authenticator: Authenticator
): UserRepository {

    override fun getUserByUsername(username: String): Flow<User> {
        return userDao.getUserByUsername(username).map {
            it.toDomain()
        }
    }

    override suspend fun existsUser(): Boolean {
        return userDao.existsUser(authenticator.uid)
    }

    override suspend fun existsUserWithEmail(email: String): Boolean {
        return userDao.existsUserWithEmail(email)
    }

    override suspend fun existsUserWithPhone(phone: String): Boolean {
        return userDao.existsUserWithPhone(phone)
    }

    override suspend fun existsUserWithUsername(username: String): Boolean {
        return userDao.existsUserWithUsername(username)
    }

    override suspend fun addUser(user: User) {
        userDao.upsertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}