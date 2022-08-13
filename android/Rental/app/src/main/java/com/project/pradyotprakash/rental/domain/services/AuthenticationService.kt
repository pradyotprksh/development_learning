package com.project.pradyotprakash.rental.domain.services

import com.google.firebase.auth.FirebaseUser
import com.project.pradyotprakash.rental.core.response.RenterResponse

interface AuthenticationService {
    fun currentUser(): FirebaseUser?

    fun isUserLoggedIn(): Boolean

    fun createUserUsingEmailPassword(email: String, password: String, result: (RenterResponse<*>) -> Unit)

    fun signInUserUsingEmailPassword(email: String, password: String, result: (RenterResponse<*>) -> Unit)
}