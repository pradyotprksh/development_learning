package com.pradyotprakash.libraryowner.domain.repositories

interface AuthenticationRepository {
    fun isUserLoggedIn(): Boolean
}