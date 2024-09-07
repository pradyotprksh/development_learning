package com.pradyotprakash.xfullstack.features.verification.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Verification.GENERATE_OTP
import utils.Constants.Paths.Verification.USER_PRESENT
import utils.Constants.Paths.Verification.VALIDATE_OTP
import utils.Constants.Paths.Verification.VERIFICATION

@Resource(VERIFICATION)
class VerificationResource {
    @Resource(GENERATE_OTP)
    data class GenerateOtpResource(
        private val parent: VerificationResource = VerificationResource(),
        val value: String,
    )

    @Resource(VALIDATE_OTP)
    data class ValidateOtpResource(
        private val parent: VerificationResource = VerificationResource(),
    )

    @Resource(USER_PRESENT)
    data class UserPresentResource(
        private val parent: VerificationResource = VerificationResource(),
        val value: String,
    )
}