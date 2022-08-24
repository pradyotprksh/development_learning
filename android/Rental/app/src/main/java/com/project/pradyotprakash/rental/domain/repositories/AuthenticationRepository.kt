package com.project.pradyotprakash.rental.domain.repositories

import com.google.firebase.auth.AuthResult
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.domain.services.AuthenticationService
import com.project.pradyotprakash.rental.domain.services.FirebaseAuthenticationService

class AuthenticationRepository(
    private val firebaseAuthenticationService: FirebaseAuthenticationService,
    private val authenticationService: AuthenticationService,
) {
    fun getCurrentUser() = firebaseAuthenticationService.currentUser()

    fun isUserLoggedIn() = firebaseAuthenticationService.isUserLoggedIn()

    fun logoutUser() = firebaseAuthenticationService.logoutUser()

    fun signInUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<AuthResult>) -> Unit
    ) {
        firebaseAuthenticationService.signInUserUsingEmailPassword(
            email = email,
            password = password
        ) {
            result(it)
        }
    }

    fun createUserWithEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<AuthResult>) -> Unit
    ) {
        firebaseAuthenticationService.createUserUsingEmailPassword(
            email = email,
            password = password
        ) {
            result(it)
        }
    }

    suspend fun getCurrentUserDetails(userId: String) = authenticationService
        .getUserDetails(userId = userId).parseResponse()

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
    ) =
        authenticationService
            .setUserDetails(
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
    ) =
        authenticationService
            .updateUserDetails(
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
            ).parseResponse()
}