package com.zeni.auth.domain.use_cases

import com.zeni.core.domain.utils.Authenticator
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(
    ) {
        TODO("Implement login")
    }
}