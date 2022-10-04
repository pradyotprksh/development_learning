package com.project.pradyotprakash.rental.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.project.pradyotprakash.rental.core.response.RenterException
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.services.FirebaseAuthenticationService
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationDataRepository(
    private val auth: FirebaseAuth,
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
        RenterResponse.Error(RenterException(message = e.localizedMessage ?: ""))
    }

    override suspend fun signInUserUsingEmailPassword(
        email: String,
        password: String
    ) = try {
        val createUserResult = auth.signInWithEmailAndPassword(email, password).await()
        RenterResponse.Success(createUserResult)
    } catch (e: Exception) {
        RenterResponse.Error(RenterException(message = e.localizedMessage ?: ""))
    }

    override fun logoutUser() {
        auth.signOut()
    }
}