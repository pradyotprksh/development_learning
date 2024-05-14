package com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate

import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.server.application.ApplicationCall

interface AuthenticateController {
    suspend fun authenticateUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Authenticate,
    )
}