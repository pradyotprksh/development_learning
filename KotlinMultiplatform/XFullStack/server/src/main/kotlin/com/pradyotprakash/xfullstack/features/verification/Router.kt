package com.pradyotprakash.xfullstack.features.verification

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.verification.controllers.VerificationController
import com.pradyotprakash.xfullstack.features.verification.resource.VerificationResource
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.verification(
    verificationController: VerificationController,
    userDataSource: UserDataSource,
) {
    get<VerificationResource.GenerateOtp> {
        verificationController.generateOtp(
            call = this.context,
            resource = it,
        )
    }

    get<VerificationResource.UserPresent> {
        verificationController.userPresent(
            call = this.context,
            resource = it,
            userDataSource = userDataSource,
        )
    }

    post<VerificationResource.ValidateOtp> {
        verificationController.validateOtp(
            call = this.context,
        )
    }
}