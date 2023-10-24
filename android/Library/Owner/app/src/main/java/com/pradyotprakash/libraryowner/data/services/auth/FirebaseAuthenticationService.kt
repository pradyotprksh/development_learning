package com.pradyotprakash.libraryowner.data.services.auth

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthenticationService {
    fun currentUser(): FirebaseUser?

    fun isUserLoggedIn(): Boolean
}