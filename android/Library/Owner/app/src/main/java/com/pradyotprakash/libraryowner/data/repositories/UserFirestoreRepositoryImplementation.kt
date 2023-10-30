package com.pradyotprakash.libraryowner.data.repositories

import com.pradyotprakash.libraryowner.data.services.firestore.UserFirestoreService
import com.pradyotprakash.libraryowner.domain.repositories.UserFirestoreRepository

class UserFirestoreRepositoryImplementation(
    private val userFirestoreService: UserFirestoreService,
) : UserFirestoreRepository {
    override suspend fun getUserDetails(userId: String) =
        userFirestoreService.getUserDetails(userId)
}