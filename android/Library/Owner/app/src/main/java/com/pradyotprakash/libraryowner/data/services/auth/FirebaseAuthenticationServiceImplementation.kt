package com.pradyotprakash.libraryowner.data.services.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pradyotprakash.libraryowner.data.services.crashlytics.CrashlyticsService

class FirebaseAuthenticationServiceImplementation(
    private val firebaseAuth: FirebaseAuth,
    private val crashlyticsService: CrashlyticsService,
): FirebaseAuthenticationService {
    override fun currentUser(): FirebaseUser? = firebaseAuth.currentUser

    override fun isUserLoggedIn(): Boolean = currentUser() != null
}