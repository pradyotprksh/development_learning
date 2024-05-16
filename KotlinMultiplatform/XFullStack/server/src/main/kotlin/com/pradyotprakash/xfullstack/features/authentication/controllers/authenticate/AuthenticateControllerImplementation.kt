package com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate

import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import utils.ResponseStatus
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class AuthenticateControllerImplementation : AuthenticateController {
    override suspend fun authenticateUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Authenticate,
    ) {
        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(status = ResponseStatus.Success, errorCode = null, data = null)
        )
    }
}