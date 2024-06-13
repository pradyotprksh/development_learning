package com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate

import io.ktor.server.application.ApplicationCall

interface AuthenticateController {
    suspend fun authenticateUser(
        call: ApplicationCall,
    )
}