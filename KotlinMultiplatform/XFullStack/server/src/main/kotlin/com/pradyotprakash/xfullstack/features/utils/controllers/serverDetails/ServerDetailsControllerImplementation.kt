package com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails

import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import utils.Localization
import utils.XFullStackResponseStatus

class ServerDetailsControllerImplementation : ServerDetailsController {
    override suspend fun isServerAvailable(
        call: ApplicationCall,
        resource: UtilsResource.ServerAvailable
    ) {
        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.SERVER_IS_AVAILABLE,
                data = null
            )
        )
    }
}