package com.pradyotprakash.xfullstack.features.authentication.controllers.login

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SaltedHash
import com.pradyotprakash.xfullstack.core.security.token.TokenClaim
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.request.LoginRequest
import com.pradyotprakash.xfullstack.data.response.AuthenticationResponse
import com.pradyotprakash.xfullstack.data.response.XFullStackResponse
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import core.exception.UserAuthDetailsError
import core.exception.UserDetailsNotFound
import core.utils.Constants.Keys.USER_ID
import core.utils.ResponseStatus
import core.utils.UtilsMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class LoginControllerImplementation : LoginController {
    override suspend fun loginUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Login,
        hashingService: HashingService,
        tokenService: TokenService,
        userDataSource: UserDataSource,
        tokenConfig: TokenConfig,
    ) {
        val loginRequest = call.receive<LoginRequest>()

        UtilsMethod.isValidUserName(loginRequest.username)
        UtilsMethod.isValidPassword(loginRequest.password)

        val user =
            userDataSource.getUserByUsername(loginRequest.username) ?: throw UserDetailsNotFound

        val isValidPassword = hashingService.verify(
            value = loginRequest.password, saltedHash = SaltedHash(
                hash = user.password, salt = user.salt
            )
        )
        if (!isValidPassword) {
            throw UserAuthDetailsError
        }

        val token = tokenService.generate(
            config = tokenConfig,
            claims = arrayOf(
                TokenClaim(
                    name = USER_ID,
                    value = user.id.toHexString()
                )
            )
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = XFullStackResponse(
                status = ResponseStatus.Success,
                data = AuthenticationResponse(
                    token = token
                )
            )
        )
    }
}