package com.zeni.core.data.mappers

import com.zeni.core.data.database.entities.UserEntity
import com.zeni.core.domain.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        username = username,
        email = email,
        phone = phone,
        birthdate = birthdate,
        address = address,
        country = country
    )
}