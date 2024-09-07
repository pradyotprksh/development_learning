package com.pradyotprakash.xfullstack.features.follow

import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.follow.controllers.FollowController
import com.pradyotprakash.xfullstack.features.follow.resource.FollowResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.follow(
    followController: FollowController,
    userDataSource: UserDataSource,
    followDataSource: FollowDataSource,
) {
    authenticate {
        post<FollowResource.UpdateResource> {
            followController.updateFollower(
                this.context,
                it,
                userDataSource,
                followDataSource,
            )
        }
    }
}