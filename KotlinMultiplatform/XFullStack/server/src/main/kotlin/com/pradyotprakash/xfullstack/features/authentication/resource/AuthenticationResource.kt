package com.pradyotprakash.xfullstack.features.authentication.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Authentication.AUTH
import utils.Constants.Paths.Authentication.AUTHENTICATE
import utils.Constants.Paths.Authentication.LOGIN
import utils.Constants.Paths.Authentication.REGISTER
import utils.Constants.Paths.Authentication.USER_INFO

@Resource(AUTH)
class AuthenticationResource {
    @Resource(LOGIN)
    data class LoginResource(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(REGISTER)
    data class RegisterResource(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(AUTHENTICATE)
    data class AuthenticateResource(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(USER_INFO)
    data class UserInfoResource(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )
}