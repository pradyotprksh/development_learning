package com.pradyotprakash.xfullstack.features.verification.controllers.userVerification

import com.pradyotprakash.xfullstack.features.verification.resource.VerificationResource
import io.ktor.server.application.ApplicationCall

interface UserVerificationController {
    suspend fun generateOtp(
        call: ApplicationCall,
        resource: VerificationResource.GenerateOtp,
    )

    suspend fun validateOtp(
        call: ApplicationCall,
        resource: VerificationResource.ValidateOtp,
    )
}