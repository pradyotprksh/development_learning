package com.pradyotprakash.notes.domain.usecases

import com.pradyotprakash.notes.core.utils.CoreUtilsMethod
import com.pradyotprakash.notes.device.entity.User
import com.pradyotprakash.notes.domain.repositories.UserRepository
import java.time.LocalDate
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    fun isUsersAvailable() = userRepository.getAllUser().isNotEmpty()

    fun isUsernameTaken(username: String) =
        userRepository.getUsersByUsername(username = username).isNotEmpty()

    fun isEmailIdUsed(emailId: String) =
        userRepository.getUsersByEmailId(emailId = emailId).isNotEmpty()

    fun createUser(
        username: String,
        firstName: String,
        lastName: String,
        emailId: String,
        password: String,
    ): Boolean {
        val user = User(
            username = username,
            firstName = firstName,
            lastName = lastName,
            emailId = emailId,
            password = password,
            createdOn = CoreUtilsMethod.getCurrentTimestamp(),
        )

        userRepository.createUser(user = user)

        val userDetails = userRepository.getUsersByUsername(username = username)
        return userDetails.isNotEmpty() && userDetails.size == 1
    }
}