package com.pradyotprakash.xfullstack.features.authentication.controllers.login

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface LoginController {
    suspend fun loginUser(
        call: ApplicationCall,
        hashingService: HashingService,
        tokenService: TokenService,
        userDataSource: UserDataSource,
        tokenConfig: TokenConfig,
    )
}