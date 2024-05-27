package com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails

import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import utils.Localization
import utils.ResponseStatus

class ServerDetailsControllerImplementation : ServerDetailsController {
    override suspend fun isServerAvailable(
        call: ApplicationCall,
        resource: UtilsResource.ServerAvailable
    ) {
        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = ResponseStatus.Success,
                code = null,
                message = Localization.SERVER_IS_AVAILABLE,
                data = null
            )
        )
    }
}