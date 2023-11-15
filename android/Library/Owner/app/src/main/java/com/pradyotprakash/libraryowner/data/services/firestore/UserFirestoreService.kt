package com.pradyotprakash.libraryowner.data.services.firestore

import com.pradyotprakash.libraryowner.core.models.User

interface UserFirestoreService {
    suspend fun getUserDetails(userId: String): User?

    suspend fun setUserDetails(user: User)
}