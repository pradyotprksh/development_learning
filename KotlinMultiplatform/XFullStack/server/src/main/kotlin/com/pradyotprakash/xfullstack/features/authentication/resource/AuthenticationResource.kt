package com.pradyotprakash.xfullstack.features.authentication.resource

import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.AUTHENTICATION
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.LOGIN
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.REGISTER
import io.ktor.resources.Resource

@Resource(AUTHENTICATION)
class AuthenticationResource {
    @Resource(LOGIN)
    data class Login(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(REGISTER)
    data class Register(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )
}