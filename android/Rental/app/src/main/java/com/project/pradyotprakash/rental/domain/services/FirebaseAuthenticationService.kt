package com.project.pradyotprakash.rental.domain.services

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.project.pradyotprakash.rental.core.response.RenterResponse
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthenticationService {
    fun currentUser(): FirebaseUser?

    fun isUserLoggedIn(): Boolean

    suspend fun createUserUsingEmailPassword(
        email: String,
        password: String,
    ) : Flow<RenterResponse<AuthResult>>

    suspend fun signInUserUsingEmailPassword(
        email: String,
        password: String,
    ) : Flow<RenterResponse<AuthResult>>

    fun logoutUser()
}