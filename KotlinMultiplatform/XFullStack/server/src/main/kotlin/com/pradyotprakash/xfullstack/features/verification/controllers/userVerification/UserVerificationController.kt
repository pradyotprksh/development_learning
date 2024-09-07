package com.pradyotprakash.xfullstack.features.verification.controllers.userVerification

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.verification.resource.VerificationResource
import io.ktor.server.application.ApplicationCall

interface UserVerificationController {
    suspend fun generateOtp(
        call: ApplicationCall,
        resource: VerificationResource.GenerateOtpResource,
    )

    suspend fun validateOtp(
        call: ApplicationCall,
    )

    suspend fun userPresent(
        call: ApplicationCall,
        resource: VerificationResource.UserPresentResource,
        userDataSource: UserDataSource,
    )
}