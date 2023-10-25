package com.pradyotprakash.libraryowner.domain.repositories

import com.pradyotprakash.libraryowner.core.models.User

interface UserFirestoreRepository {
    suspend fun getUserDetails(userId: String): User?
}