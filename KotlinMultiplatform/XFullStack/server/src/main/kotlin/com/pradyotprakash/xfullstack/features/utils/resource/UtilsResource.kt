package com.pradyotprakash.xfullstack.features.utils.resource

import core.utils.Constants.Paths.Utils.GENERATE_OTP
import core.utils.Constants.Paths.Utils.USERNAME_VALID
import core.utils.Constants.Paths.Utils.UTILS
import core.utils.Constants.Paths.Utils.VALIDATE_OTP
import io.ktor.resources.Resource

@Resource(UTILS)
class UtilsResource {

    @Resource(USERNAME_VALID)
    data class UsernameValid(
        private val parent: UtilsResource = UtilsResource(),
        val username: String,
    )

    @Resource(GENERATE_OTP)
    data class GenerateOtp(
        private val parent: UtilsResource = UtilsResource(),
        val value: String,
    )

    @Resource(VALIDATE_OTP)
    data class ValidateOtp(
        private val parent: UtilsResource = UtilsResource(),
    )
}