package com.zeni.core.domain.repository

import com.zeni.core.domain.model.User

interface UserRepository {

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)
}