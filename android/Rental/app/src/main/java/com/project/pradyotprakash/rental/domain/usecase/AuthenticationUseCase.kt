package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.AuthenticationRepository
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val basicRepository: BasicRepository,
) {
    fun getCurrentUser() = authenticationRepository.getCurrentUser()

    fun isUserLoggedIn() = authenticationRepository.isUserLoggedIn()

    suspend fun signInUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>?) -> Unit
    ) {
        basicRepository.isEmailAddressValid(emailAddress = email).let { emailResult ->
            when (emailResult) {
                is RenterResponse.Success -> {
                    authenticationRepository.signInUserWithEmailPassword(
                        email = email,
                        password = password
                    ) {
                        result(it)
                    }
                }
                else -> result(emailResult)
            }
        }
    }

    suspend fun createUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>?) -> Unit
    ) {
        basicRepository.isEmailAddressValid(emailAddress = email).let { emailResult ->
            when (emailResult) {
                is RenterResponse.Success -> {
                    authenticationRepository.createUserWithEmailPassword(
                        email = email,
                        password = password
                    ) {
                        result(it)
                    }
                }
                else -> result(emailResult)
            }
        }
    }
}