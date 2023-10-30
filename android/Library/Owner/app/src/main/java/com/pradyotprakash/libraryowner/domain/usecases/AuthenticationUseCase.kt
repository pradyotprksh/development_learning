package com.pradyotprakash.libraryowner.domain.usecases

import com.pradyotprakash.libraryowner.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    fun isUserLoggedIn() = authenticationRepository.isUserLoggedIn()

    fun getCurrentUserId() = authenticationRepository.currentUser()?.uid
}