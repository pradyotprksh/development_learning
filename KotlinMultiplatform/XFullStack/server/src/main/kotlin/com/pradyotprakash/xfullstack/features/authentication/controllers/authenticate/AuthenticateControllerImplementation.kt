package com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate

import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Localization
import utils.XFullStackResponseStatus

class AuthenticateControllerImplementation : AuthenticateController {
    override suspend fun authenticateUser(
        call: ApplicationCall,
    ) {
        delay(API_RESPONSE_DELAY)

        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.USER_AUTHENTICATED,
                data = null
            )
        )
    }
}