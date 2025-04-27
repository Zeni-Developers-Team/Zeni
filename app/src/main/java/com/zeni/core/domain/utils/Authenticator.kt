package com.zeni.core.domain.utils

import android.app.Activity
import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import com.zeni.auth.domain.model.LoginErrors
import com.zeni.auth.domain.utils.RegisterResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor() {
    private val auth = Firebase.auth

    /**
     * The current user in the server.
     */
    private val currentUser: FirebaseUser?
        get() = auth.currentUser

    /**
     * The unique identifier of the user in the server.
     */
    val uid: String
        get() = currentUser!!.uid

    /**
     * The email of the user in the server.
     */
    val email: String?
        get() = currentUser!!.email

    /**
     * The email verification status.
     */
    val isEmailVerified: Boolean
        get() = currentUser!!.isEmailVerified

    suspend fun isEmailVerifiedWithReload(): Boolean {
        return try {
            if (!currentUser!!.isEmailVerified) {
                currentUser!!.reload().await()
            }

            currentUser!!.isEmailVerified
        } catch (e: FirebaseException) {
            false
        }
    }

    /**
     * The phone number of the user in the server.
     */
    val phoneNumber: String?
        get() = currentUser!!.phoneNumber

    /**
     * The log in status.
     */
    val isLogged: Boolean
        get() = currentUser != null

    suspend fun login(
        email: String,
        password: String
    ): LoginErrors {
        return try {
            TODO("Verify email")
            auth.signInWithEmailAndPassword(email, password)
                .await()
            LoginErrors.NONE
        } catch (_: FirebaseAuthInvalidUserException) {
            LoginErrors.INVALID_IDENTIFIER
        } catch (_: FirebaseAuthInvalidCredentialsException) {
            LoginErrors.INVALID_PASSWORD
        } catch (e: Exception) {
            LoginErrors.FATAL_ERROR
        }
    }

    suspend fun register(
        email: String,
        password: String
    ): RegisterResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
                .await()

            if (result.user != null) {
                currentUser!!.sendEmailVerification()
                    .await()

                RegisterResult.CodeSent
            } else {
                RegisterResult.Error(TODO("Error de registro"))
            }
        } catch (e: Exception) {
            // Handle error
            RegisterResult.Error(TODO("Error de registro: ${e.message}"))
        }
    }

    fun logout() {
        auth.signOut()
    }
}