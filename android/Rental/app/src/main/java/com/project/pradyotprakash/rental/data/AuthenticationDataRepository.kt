package com.project.pradyotprakash.rental.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.response.RenterException
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.services.AuthenticationService

class AuthenticationDataRepository(
    private val auth: FirebaseAuth,
) : AuthenticationService {
    override fun currentUser(): FirebaseUser? = auth.currentUser

    override fun isUserLoggedIn(): Boolean = currentUser() != null

    override fun createUserUsingEmailPassword(
        email: String,
        password: String,
        result: (RenterResponse<*>) -> Unit,
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
        result: (RenterResponse<*>) -> Unit,
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
}