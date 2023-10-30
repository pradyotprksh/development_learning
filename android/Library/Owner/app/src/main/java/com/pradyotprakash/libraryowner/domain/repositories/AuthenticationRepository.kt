package com.pradyotprakash.libraryowner.domain.repositories

import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {
    fun isUserLoggedIn(): Boolean

    fun currentUser(): FirebaseUser?
}