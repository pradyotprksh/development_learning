package com.pradyotprakash.xfullstack.features.utils.controllers.userVerification

import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import io.ktor.server.application.ApplicationCall

class UserVerificationControllerImplementation : UserVerificationController {
    override fun generateOtp(call: ApplicationCall, resource: UtilsResource.GenerateOtp) {
        TODO("Not yet implemented")
    }

    override fun validateOtp(call: ApplicationCall, resource: UtilsResource.ValidateOtp) {
        TODO("Not yet implemented")
    }
}