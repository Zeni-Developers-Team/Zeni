package com.zeni.core.domain.repository

import com.zeni.core.domain.model.User

interface UserRepository {

    suspend fun existsUser(): Boolean

    suspend fun existsUserWithEmail(email: String): Boolean

    suspend fun existsUserWithPhone(phone: String): Boolean

    suspend fun existsUserWithUsername(username: String): Boolean

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)
}