package com.pradyotprakash.notes.domain.usecases

import com.pradyotprakash.notes.domain.repositories.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun isUsersAvailable() = userRepository.getAllUser().isNotEmpty()

    suspend fun isUsernameTaken(username: String) =
        userRepository.getUsersByUsername(username = username).isNotEmpty()

    suspend fun isEmailIdUsed(emailId: String) =
        userRepository.getUsersByEmailId(emailId = emailId).isNotEmpty()
}