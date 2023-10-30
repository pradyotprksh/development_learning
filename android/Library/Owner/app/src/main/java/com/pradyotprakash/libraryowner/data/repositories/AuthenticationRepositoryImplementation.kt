package com.pradyotprakash.libraryowner.data.repositories

import com.pradyotprakash.libraryowner.data.services.auth.FirebaseAuthenticationService
import com.pradyotprakash.libraryowner.domain.repositories.AuthenticationRepository

class AuthenticationRepositoryImplementation(
    private val firebaseAuthenticationService: FirebaseAuthenticationService,
) : AuthenticationRepository {
    override fun isUserLoggedIn() = firebaseAuthenticationService.isUserLoggedIn()

    override fun currentUser() = firebaseAuthenticationService.currentUser()
}