package com.project.pradyotprakash.rental.data.repositories

import android.net.Uri
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.project.pradyotprakash.rental.core.response.RenterException
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.core.services.FirebaseAuthenticationService
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationDataRepository(
    private val auth: FirebaseAuth,
    private val crashlyticsService: CrashlyticsService,
) : FirebaseAuthenticationService {
    override fun currentUser(): FirebaseUser? = auth.currentUser

    override fun isUserLoggedIn(): Boolean = currentUser() != null

    override suspend fun createUserUsingEmailPassword(
        email: String,
        password: String
    ): RenterResponse<AuthResult> = try {
        val createUserResult = auth.createUserWithEmailAndPassword(email, password).await()
        RenterResponse.Success(createUserResult)
    } catch (e: Exception) {
        crashlyticsService.submitCaughtException(e)
        RenterResponse.Error(RenterException(message = e.localizedMessage ?: ""))
    }

    override suspend fun signInUserUsingEmailPassword(
        email: String,
        password: String
    ) = try {
        val createUserResult = auth.signInWithEmailAndPassword(email, password).await()
        RenterResponse.Success(createUserResult)
    } catch (e: Exception) {
        crashlyticsService.submitCaughtException(e)
        RenterResponse.Error(RenterException(message = e.localizedMessage ?: ""))
    }

    override fun logoutUser() {
        auth.signOut()
    }

    override fun updateUserDetails(fullName: String, profilePic: String) {
        currentUser()?.let { currentUser ->
            val profileUpdates = userProfileChangeRequest {
                if (fullName.isNotBlank()) {
                    displayName = fullName
                }
                if (profilePic.isNotBlank()) {
                    photoUri = Uri.parse(profilePic)
                }
            }

            currentUser.updateProfile(profileUpdates)
        }
    }
}