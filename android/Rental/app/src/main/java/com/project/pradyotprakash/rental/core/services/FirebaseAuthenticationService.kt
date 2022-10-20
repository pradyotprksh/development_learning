package com.project.pradyotprakash.rental.core.services

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.project.pradyotprakash.rental.core.response.RenterResponse

interface FirebaseAuthenticationService {
    fun currentUser(): FirebaseUser?

    fun isUserLoggedIn(): Boolean

    suspend fun createUserUsingEmailPassword(
        email: String,
        password: String,
    ) : RenterResponse<AuthResult>

    suspend fun signInUserUsingEmailPassword(
        email: String,
        password: String,
    ) : RenterResponse<AuthResult>

    fun logoutUser()

    fun updateUserDetails(fullName: String, profilePic: String)
}