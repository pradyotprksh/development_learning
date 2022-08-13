package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.services.AuthenticationService

class AuthenticationRepository(
    private val authenticationService: AuthenticationService,
) {
    fun getCurrentUser() = authenticationService.currentUser()

    fun isUserLoggedIn() = authenticationService.isUserLoggedIn()

    fun signInUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        authenticationService.signInUserUsingEmailPassword(email = email, password = password) {
            result(it)
        }
    }

    fun createUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        authenticationService.createUserUsingEmailPassword(email = email, password = password) {
            result(it)
        }
    }
}