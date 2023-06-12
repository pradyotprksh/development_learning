package com.pradyotprakash.jwitter.presenter

import com.pradyotprakash.jwitter.core.services.UserService

class UserPresenter(
    private val userService: UserService,
) {
    suspend fun createUser(
        email: String,
        password: String,
    ) = userService.createUser(
        email = email,
        password = password,
    )
}