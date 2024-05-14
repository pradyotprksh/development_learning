package com.pradyotprakash.xfullstack.features.authentication.resource

import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.AUTH
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.AUTHENTICATE
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.LOGIN
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.REGISTER
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Authentication.USER_INFO
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