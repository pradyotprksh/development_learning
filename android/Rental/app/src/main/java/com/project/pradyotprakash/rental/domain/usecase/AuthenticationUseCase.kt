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

    suspend fun setUserDetails(
        userId: String,
        firstName: String,
        lastName: String,
        permanentAddress: String,
        dateOfBirth: String,
        emailAddress: String,
        profession: String,
        phoneNumber: String,
        profilePicUrl: String,
        userType: UserType,
    ) = authenticationRepository.setCurrentUserDetails(
        userId = userId,
        firstName = firstName,
        lastName = lastName,
        permanentAddress = permanentAddress,
        dateOfBirth = dateOfBirth,
        emailAddress = emailAddress,
        profession = profession,
        phoneNumber = phoneNumber,
        profilePicUrl = profilePicUrl,
        userType = userType,
    )

    suspend fun updateUserDetails(
        userId: String,
        firstName: String = "",
        lastName: String = "",
        permanentAddress: String = "",
        dateOfBirth: String = "",
        emailAddress: String = "",
        profession: String = "",
        phoneNumber: String = "",
        profilePicUrl: String = "",
        userType: UserType,
        isAllDetailsAvailable: Boolean,
    ) = authenticationRepository.updateCurrentUserDetails(
        userId = userId,
        firstName = firstName,
        lastName = lastName,
        permanentAddress = permanentAddress,
        dateOfBirth = dateOfBirth,
        emailAddress = emailAddress,
        profession = profession,
        phoneNumber = phoneNumber,
        profilePicUrl = profilePicUrl,
        userType = userType,
        isAllDetailsAvailable = isAllDetailsAvailable,
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