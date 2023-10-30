package com.pradyotprakash.libraryowner.domain.usecases

import com.pradyotprakash.libraryowner.domain.repositories.UserFirestoreRepository
import javax.inject.Inject

class UserFirestoreUseCase @Inject constructor(
    private val userFirestoreRepository: UserFirestoreRepository,
) {
    suspend fun isUserDetailsAvailable(userId: String) = if (userId.isNotBlank()) {
        userFirestoreRepository.getUserDetails(userId)?.isAllDetailsAdded ?: false
    } else {
        false
    }
}