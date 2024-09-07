package com.pradyotprakash.xfullstack.features.utils.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Utils.SERVER_AVAILABLE
import utils.Constants.Paths.Utils.USERNAME_VALID
import utils.Constants.Paths.Utils.UTILS

@Resource(UTILS)
class UtilsResource {

    @Resource(USERNAME_VALID)
    data class UsernameValidResource(
        private val parent: UtilsResource = UtilsResource(),
        val value: String,
    )

    @Resource(SERVER_AVAILABLE)
    data class ServerAvailableResource(
        private val parent: UtilsResource = UtilsResource(),
    )
}