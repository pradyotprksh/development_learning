package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.app.utils.UserType
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

    fun getCurrentUserId() = authenticationRepository.getCurrentUser()?.uid

    fun logoutUser() = authenticationRepository.logoutUser()

    suspend fun getCurrentUserDetails(userId: String) = authenticationRepository
        .getCurrentUserDetails(userId = userId)

    suspend fun setUserType(
        userId: String,
        emailAddress: String,
        userType: UserType,
    ) = authenticationRepository.setCurrentUserDetails(
        userId = userId,
        firstName = "",
        lastName = "",
        permanentAddress = "",
        dateOfBirth = -1,
        emailAddress = emailAddress,
        profession = "",
        phoneNumber = "",
        profilePicUrl = "",
        userType = userType,
    )

    suspend fun signInUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
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
        result: (RenterResponse<*>) -> Unit
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