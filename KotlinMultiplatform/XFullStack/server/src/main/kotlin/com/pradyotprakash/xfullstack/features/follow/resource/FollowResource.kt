package com.pradyotprakash.xfullstack.features.follow.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Follow.FOLLOW
import utils.Constants.Paths.Follow.UPDATE

@Resource(FOLLOW)
class FollowResource {

    @Resource(UPDATE)
    data class Update(
        private val parent: FollowResource = FollowResource(),
        val followingId: String,
    )
}