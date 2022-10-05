package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.data.services.AuthenticationService
import com.project.pradyotprakash.rental.core.services.FirebaseAuthenticationService

class AuthenticationRepository(
    private val firebaseAuthenticationService: FirebaseAuthenticationService,
    private val authenticationService: AuthenticationService,
) {
    fun getCurrentUser() = firebaseAuthenticationService.currentUser()

    fun isUserLoggedIn() = firebaseAuthenticationService.isUserLoggedIn()

    fun logoutUser() = firebaseAuthenticationService.logoutUser()

    suspend fun signInUserWithEmailPassword(
        email: String, password: String
    ) = firebaseAuthenticationService.signInUserUsingEmailPassword(
        email = email, password = password
    )

    suspend fun createUserWithEmailPassword(
        email: String, password: String
    ) = firebaseAuthenticationService.createUserUsingEmailPassword(
        email = email, password = password
    )

    suspend fun getCurrentUserDetails(userId: String, appCheckToken: String) =
        authenticationService.getUserDetails(userId = userId, appCheckToken = appCheckToken)
            .parseResponse()

    suspend fun setCurrentUserDetails(
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
    ) = authenticationService.setUserDetails(
            userId = userId,
            firstName = firstName,
            lastName = lastName,
            permanentAddress = permanentAddress,
            dateOfBirth = dateOfBirth,
            emailAddress = emailAddress,
            profession = profession,
            phoneNumber = phoneNumber,
            profilePicUrl = profilePicUrl,
            userType = userType.name,
            appCheckToken = appCheckToken,
        ).parseResponse()

    suspend fun updateCurrentUserDetails(
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
        isAllDetailsAvailable: Boolean,
        appCheckToken: String,
    ) = authenticationService.updateUserDetails(
            userId = userId,
            firstName = firstName,
            lastName = lastName,
            permanentAddress = permanentAddress,
            dateOfBirth = dateOfBirth,
            emailAddress = emailAddress,
            profession = profession,
            phoneNumber = phoneNumber,
            profilePicUrl = profilePicUrl,
            userType = userType.name,
            isAllDetailsAvailable = isAllDetailsAvailable,
            appCheckToken = appCheckToken,
        ).parseResponse()
}