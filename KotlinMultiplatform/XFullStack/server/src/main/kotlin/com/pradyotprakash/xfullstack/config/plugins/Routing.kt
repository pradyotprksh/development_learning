package com.pradyotprakash.xfullstack.config.plugins

import com.pradyotprakash.xfullstack.config.ModulesConfig
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.authentication
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.utils.controllers.UtilsController
import com.pradyotprakash.xfullstack.features.utils.utils
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.instance

fun Application.configureRouting() {
    val userDataSource by ModulesConfig.di.instance<UserDataSource>()
    val tokenService by ModulesConfig.di.instance<TokenService>()
    val tokenConfig by ModulesConfig.di.instance<TokenConfig>()
    val hashingService by ModulesConfig.di.instance<HashingService>()
    val authenticationController by ModulesConfig.di.instance<AuthenticationController>()
    val utilsController by ModulesConfig.di.instance<UtilsController>()

    routing {
        authentication(
            authenticationController = authenticationController,
            hashingService = hashingService,
            tokenService = tokenService,
            userDataSource = userDataSource,
            tokenConfig = tokenConfig,
        )
        utils(
            userDataSource = userDataSource,
            utilsController = utilsController,
        )
    }
}