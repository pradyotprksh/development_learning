package com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate

import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.follow.resource.FollowResource
import io.ktor.server.application.ApplicationCall

interface FollowUpdateController {
    suspend fun updateFollower(
        call: ApplicationCall,
        resource: FollowResource.Update,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
    )
}