package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.AuthenticationRepository
import com.project.pradyotprakash.rental.domain.repositories.BasicRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * A use case class for the authentication, which will be used
 * by view model to update or get the details.
 *
 * @param authenticationRepository The authorization repository class
 * @param basicRepository The repository which handles all the basic functionality in the
 * application on the data level
 */
class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val basicRepository: BasicRepository,
) {
    /**
     * Get the current user details
     *
     * @return FirebaseUser object
     */
    fun getCurrentUser() = authenticationRepository.getCurrentUser()

    /**
     * Check if the user is logged in
     */
    fun isUserLoggedIn() = authenticationRepository.isUserLoggedIn()

    /**
     * Get the user user id
     */
    fun getCurrentUserId() = authenticationRepository.getCurrentUser()?.uid

    /**
     * Logout the current user
     */
    fun logoutUser() = authenticationRepository.logoutUser()

    /**
     * Get the user details from the DB. Update with the UserEntity.
     *
     * @param userId Id of the user for which the details is needed
     */
    suspend fun getCurrentUserDetails(userId: String, appCheckToken: String) =
        flow {
            emit(RenterResponse.Loading)
            emit(
                authenticationRepository.getCurrentUserDetails(
                    userId = userId,
                    appCheckToken = appCheckToken
                )
            )
            emit(RenterResponse.Idle)
        }

    /**
     * Set the user details
     */
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
        appCheckToken: String,
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
                appCheckToken = appCheckToken,
            ),
        )
        emit(RenterResponse.Idle)
    }

    /**
     * Update the user details with the required details.
     */
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
        appCheckToken: String,
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
                appCheckToken = appCheckToken,
            ),
        )
        emit(RenterResponse.Idle)
    }

    suspend fun signInUserWithEmailPassword(
        email: String,
        password: String,
        appCheckToken: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        basicRepository.isEmailAddressValid(emailAddress = email, appCheckToken = appCheckToken)
            .collect { emailResult ->
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
        appCheckToken: String,
        result: (RenterResponse<*>) -> Unit
    ) {
        basicRepository.isEmailAddressValid(emailAddress = email, appCheckToken = appCheckToken)
            .collect { emailResult ->
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