package com.pradyotprakash.libraryowner.data.services.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthenticationServiceImplementation(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthenticationService {
    override fun currentUser(): FirebaseUser? = firebaseAuth.currentUser

    override fun isUserLoggedIn(): Boolean = currentUser() != null
}