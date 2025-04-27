package com.zeni.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.zeni.core.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}