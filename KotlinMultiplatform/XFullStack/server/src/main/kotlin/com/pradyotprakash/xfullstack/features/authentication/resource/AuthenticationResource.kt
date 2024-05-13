package com.pradyotprakash.xfullstack.features.authentication.resource

import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.AUTHENTICATE
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.AUTHENTICATION
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.LOGIN
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.REGISTER
import io.ktor.resources.Resource

@Resource(AUTHENTICATION)
class AuthenticationResource {
    @Resource(LOGIN)
    data class Login(
        private val parent: AuthenticationResource = AuthenticationResource(),
        val username: String,
        val password: String,
    ) {
        fun isValid() = username.isNotBlank() && password.isNotBlank()

        fun isUsernameValid() = username.length >= 4

        fun isPasswordValid() = password.length >= 8
    }

    @Resource(REGISTER)
    data class Register(
        private val parent: AuthenticationResource = AuthenticationResource(),
        val username: String,
        val password: String,
        val bio: String?,
        val emailAddress: String,
        val phoneNumber: String,
        val profilePicture: String?,
        val dateOfBirth: String
    ) {
        fun isValid() = username.isNotBlank() && password.isNotBlank()

        fun isUsernameValid() = username.length >= 4

        fun isPasswordValid() = password.length >= 8
    }

    @Resource(AUTHENTICATE)
    data class Authenticate(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )
}