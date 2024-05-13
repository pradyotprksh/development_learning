package com.pradyotprakash.xfullstack.config.plugins

import com.pradyotprakash.xfullstack.config.ModulesConfig
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.authentication
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.instance

fun Application.configureRouting(
    hashingService: HashingService,
    userDataSource: UserDataSource,
) {
    val authenticationController by ModulesConfig.di.instance<AuthenticationController>()

    routing {
        authentication(
            authenticationController = authenticationController,
            hashingService = hashingService,
            userDataSource = userDataSource
        )
    }
}