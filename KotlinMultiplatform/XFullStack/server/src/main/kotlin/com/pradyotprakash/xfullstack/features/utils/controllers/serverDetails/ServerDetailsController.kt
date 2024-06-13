package com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails

import io.ktor.server.application.ApplicationCall

interface ServerDetailsController {
    suspend fun isServerAvailable(
        call: ApplicationCall,
    )
}