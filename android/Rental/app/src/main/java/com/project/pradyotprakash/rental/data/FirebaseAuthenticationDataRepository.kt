package com.project.pradyotprakash.rental.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.response.RenterException
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.services.FirebaseAuthenticationService

class FirebaseAuthenticationDataRepository(
    private val auth: FirebaseAuth,
) : FirebaseAuthenticationService {
    override fun currentUser(): FirebaseUser? = auth.currentUser

    override fun isUserLoggedIn(): Boolean = currentUser() != null

    override fun createUserUsingEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<AuthResult>) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                result(RenterResponse.Success(it))
            }
            .addOnFailureListener {
                result(
                    RenterResponse.Error(
                        RenterException(
                            message = it.localizedMessage ?: TR.noDataFoundError
                        )
                    )
                )
            }
    }

    override fun signInUserUsingEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<AuthResult>) -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                result(RenterResponse.Success(it))
            }
            .addOnFailureListener {
                result(
                    RenterResponse.Error(
                        RenterException(
                            message = it.localizedMessage ?: TR.noDataFoundError
                        )
                    )
                )
            }
    }

    override fun logoutUser() {
        auth.signOut()
    }
}