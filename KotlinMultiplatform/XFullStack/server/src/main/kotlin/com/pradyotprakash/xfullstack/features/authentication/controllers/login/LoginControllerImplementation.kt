package com.pradyotprakash.xfullstack.features.authentication.controllers.login

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SaltedHash
import com.pradyotprakash.xfullstack.core.security.token.TokenClaim
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.response.AuthenticationResponse
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
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
        if (!resource.isValid()) {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (!resource.isPasswordValid() || !resource.isUsernameValid()) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        val user = userDataSource.getUserByUsername(resource.username)
        if (user == null) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        val isValidPassword = hashingService.verify(
            value = resource.password, saltedHash = SaltedHash(
                hash = user.password, salt = user.salt
            )
        )
        if (!isValidPassword) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        val token = tokenService.generate(
            config = tokenConfig,
            claims = arrayOf(
                TokenClaim(
                    name = "userId",
                    value = user.id.toHexString()
                )
            )
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = AuthenticationResponse(
                token = token
            )
        )
    }
}