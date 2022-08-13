package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    fun getCurrentUser() = authenticationRepository.getCurrentUser()

    fun isUserLoggedIn() = authenticationRepository.isUserLoggedIn()

    fun signInUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        authenticationRepository.signInUserWithEmailPassword(email = email, password = password) {
            result(it)
        }
    }

    fun createUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        authenticationRepository.createUserWithEmailPassword(email = email, password = password) {
            result(it)
        }
    }
}