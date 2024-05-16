package com.pradyotprakash.xfullstack.features.utils.controllers.userVerification

import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import io.ktor.server.application.ApplicationCall

interface UserVerificationController {
    fun generateOtp(
        call: ApplicationCall,
        resource: UtilsResource.GenerateOtp,
    )

    fun validateOtp(
        call: ApplicationCall,
        resource: UtilsResource.ValidateOtp,
    )
}