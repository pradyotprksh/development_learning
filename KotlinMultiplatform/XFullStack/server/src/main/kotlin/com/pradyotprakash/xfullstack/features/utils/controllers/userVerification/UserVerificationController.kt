package com.pradyotprakash.xfullstack.features.utils.controllers.userVerification

import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import io.ktor.server.application.ApplicationCall

interface UserVerificationController {
    suspend fun generateOtp(
        call: ApplicationCall,
        resource: UtilsResource.GenerateOtp,
    )

    suspend fun validateOtp(
        call: ApplicationCall,
        resource: UtilsResource.ValidateOtp,
    )
}