package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.core.services.FirebaseAuthenticationService
import com.project.pradyotprakash.rental.data.services.AuthenticationService
import com.project.pradyotprakash.rental.domain.modal.LocationEntity

class AuthenticationRepository(
    private val firebaseAuthenticationService: FirebaseAuthenticationService,
    private val authenticationService: AuthenticationService,
    private val crashlytics: CrashlyticsService,
) {
    fun getCurrentUser() = firebaseAuthenticationService.currentUser()

    fun isUserLoggedIn() = firebaseAuthenticationService.isUserLoggedIn()

    fun logoutUser() = firebaseAuthenticationService.logoutUser()

    fun updateUserDetails(fullName: String, profilePic: String) =
        firebaseAuthenticationService.updateUserDetails(
            fullName = fullName,
            profilePic = profilePic
        )

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

    suspend fun getCurrentUserDetails(
        userId: String,
        latitude: String,
        longitude: String,
    ) = authenticationService.getUserDetails(
            userId = userId,
            latitude = latitude,
            longitude = longitude,
    ).parseResponse(crashlytics)

    suspend fun setCurrentUserDetails(
        userId: String,
        firstName: String,
        lastName: String,
        permanentAddress: LocationEntity,
        dateOfBirth: String,
        emailAddress: String,
        profession: String,
        phoneNumber: String,
        profilePicUrl: String,
        userType: String,
        isAllDetailsAvailable: Boolean,
    ) = authenticationService.setUserDetails(
        userId = userId,
        firstName = firstName,
        lastName = lastName,
        permanentAddress = permanentAddress.toMap(),
        dateOfBirth = dateOfBirth,
        emailAddress = emailAddress,
        profession = profession,
        phoneNumber = phoneNumber,
        profilePicUrl = profilePicUrl,
        userType = userType,
        isAllDetailsAvailable = isAllDetailsAvailable,
    ).parseResponse(crashlytics)

    suspend fun updateCurrentUserDetails(
        userId: String,
        firstName: String,
        lastName: String,
        permanentAddress: LocationEntity?,
        dateOfBirth: String,
        emailAddress: String,
        profession: String,
        phoneNumber: String,
        profilePicUrl: String,
        userType: String,
        isAllDetailsAvailable: Boolean,
    ) = authenticationService.updateUserDetails(
        userId = userId,
        firstName = firstName,
        lastName = lastName,
        permanentAddress = permanentAddress?.toMap() ?: "",
        dateOfBirth = dateOfBirth,
        emailAddress = emailAddress,
        profession = profession,
        phoneNumber = phoneNumber,
        profilePicUrl = profilePicUrl,
        userType = userType,
        isAllDetailsAvailable = isAllDetailsAvailable,
    ).parseResponse(crashlytics)
}