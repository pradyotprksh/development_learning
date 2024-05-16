package com.pradyotprakash.xfullstack.features.authentication.resource

import utils.Constants.Paths.Authentication.AUTH
import utils.Constants.Paths.Authentication.AUTHENTICATE
import utils.Constants.Paths.Authentication.LOGIN
import utils.Constants.Paths.Authentication.REGISTER
import utils.Constants.Paths.Authentication.USER_INFO
import io.ktor.resources.Resource

@Resource(AUTH)
class AuthenticationResource {
    @Resource(LOGIN)
    data class Login(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(REGISTER)
    data class Register(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(AUTHENTICATE)
    data class Authenticate(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )

    @Resource(USER_INFO)
    data class UserInfo(
        private val parent: AuthenticationResource = AuthenticationResource(),
    )
}