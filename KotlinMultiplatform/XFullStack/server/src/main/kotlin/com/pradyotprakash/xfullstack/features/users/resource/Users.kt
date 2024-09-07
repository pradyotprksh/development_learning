package com.pradyotprakash.xfullstack.features.users.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.User.CAN_FOLLOW
import utils.Constants.Paths.User.INFO
import utils.Constants.Paths.User.USER

@Resource(USER)
class UsersResource {
    @Resource(INFO)
    data class InfoResource(
        private val parent: UsersResource = UsersResource(),
        val userId: String,
    )

    @Resource(CAN_FOLLOW)
    data class CanFollowResource(
        private val parent: UsersResource = UsersResource(),
    )
}