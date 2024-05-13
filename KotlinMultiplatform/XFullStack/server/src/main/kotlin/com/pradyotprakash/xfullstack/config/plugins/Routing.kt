package com.pradyotprakash.xfullstack.config.plugins

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.authentication
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting(
    hashingService: HashingService,
    userDataSource: UserDataSource,
) {
    routing {
        authentication(
            hashingService = hashingService, userDataSource = userDataSource
        )
    }
}