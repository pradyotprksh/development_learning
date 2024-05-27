package com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails

import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import io.ktor.server.application.ApplicationCall

interface ServerDetailsController {
    suspend fun isServerAvailable(
        call: ApplicationCall,
        resource: UtilsResource.ServerAvailable,
    )
}