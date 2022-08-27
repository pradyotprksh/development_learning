package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.AuthenticationRepository
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val basicRepository: BasicRepository,
) {
    fun getCurrentUser() = authenticationRepository.getCurrentUser()

    fun isUserLoggedIn() = authenticationRepository.isUserLoggedIn()

    fun getCurrentUserId() = authenticationRepository.getCurrentUser()?.uid

    fun logoutUser() = authenticationRepository.logoutUser()

    suspend fun getCurrentUserDetails(userId: String) =
        flow {
            emit(RenterResponse.Loading)
            emit(authenticationRepository.getCurrentUserDetails(userId = userId))
            emit(RenterResponse.Idle)
        }

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
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            authenticationRepository.setCurrentUserDetails(
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
            ),
        )
        emit(RenterResponse.Idle)
    }

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
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            authenticationRepository.updateCurrentUserDetails(
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
            ),
        )
        emit(RenterResponse.Idle)
    }

    suspend fun signInUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        basicRepository.isEmailAddressValid(emailAddress = email).collect { emailResult ->
            when (emailResult) {
                is RenterResponse.Success -> {
                    authenticationRepository.signInUserWithEmailPassword(
                        email = email,
                        password = password
                    ) {
                        result(it)
                    }
                }
                is RenterResponse.Loading, is RenterResponse.Error -> result(emailResult)
                else -> {}
            }
        }
    }

    suspend fun createUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        basicRepository.isEmailAddressValid(emailAddress = email).collect { emailResult ->
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