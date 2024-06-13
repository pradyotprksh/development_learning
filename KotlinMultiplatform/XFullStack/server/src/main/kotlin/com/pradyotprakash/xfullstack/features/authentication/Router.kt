package com.pradyotprakash.xfullstack.features.authentication

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.authentication(
    authenticationController: AuthenticationController,
    hashingService: HashingService,
    tokenService: TokenService,
    userDataSource: UserDataSource,
    tokenConfig: TokenConfig,
    followDataSource: FollowDataSource,
) {
    post<AuthenticationResource.Register> {
        authenticationController.registerUser(
            call = this.context,
            hashingService = hashingService,
            userDataSource = userDataSource,
        )
    }

    post<AuthenticationResource.Login> {
        authenticationController.loginUser(
            call = this.context,
            hashingService = hashingService,
            tokenService = tokenService,
            userDataSource = userDataSource,
            tokenConfig = tokenConfig,
        )
    }

    authenticate {
        get<AuthenticationResource.Authenticate> {
            authenticationController.authenticateUser(
                call = this.context,
            )
        }

        get<AuthenticationResource.UserInfo> {
            authenticationController.getUserInfo(
                call = this.context,
                userDataSource = userDataSource,
                followDataSource = followDataSource,
            )
        }
    }
}