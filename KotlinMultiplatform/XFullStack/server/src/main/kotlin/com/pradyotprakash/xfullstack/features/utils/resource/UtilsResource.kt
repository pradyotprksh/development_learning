package com.pradyotprakash.xfullstack.features.utils.resource

import com.pradyotprakash.xfullstack.utils.Constants.Paths.Utils.USERNAME_VALID
import com.pradyotprakash.xfullstack.utils.Constants.Paths.Utils.UTILS
import io.ktor.resources.Resource

@Resource(UTILS)
class UtilsResource {

    @Resource(USERNAME_VALID)
    data class UsernameValid(
        private val parent: UtilsResource = UtilsResource(),
        val username: String,
    )
}