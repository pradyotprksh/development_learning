package com.pradyotprakash.libraryowner.data.services.auth

import com.pradyotprakash.libraryowner.domain.repositories.AuthenticationRepository

class AuthenticationRepositoryImplementation(
    private val firebaseAuthenticationService: FirebaseAuthenticationService,
): AuthenticationRepository {
    override fun isUserLoggedIn() = firebaseAuthenticationService.isUserLoggedIn()
}