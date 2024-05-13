package com.pradyotprakash.xfullstack.features.authentication

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

fun Routing.authentication(
    authenticationController: AuthenticationController,
    hashingService: HashingService,
    tokenService: TokenService,
    userDataSource: UserDataSource,
) {
    post<AuthenticationResource.Register> {
        authenticationController.registerUser(
            call = this.context,
            resource = it,
            hashingService = hashingService,
            userDataSource = userDataSource,
        )
    }

    post<AuthenticationResource.Login> {
        authenticationController.loginUser(
            call = this.context,
            resource = it,
            hashingService = hashingService,
            tokenService = tokenService,
            userDataSource = userDataSource,
        )
    }
}