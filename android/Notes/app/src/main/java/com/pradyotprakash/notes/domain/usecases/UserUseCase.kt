package com.pradyotprakash.notes.domain.usecases

import com.pradyotprakash.notes.domain.repositories.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun isUsersAvailable() = userRepository.getAllUser().isNotEmpty()
}