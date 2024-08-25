package com.pradyotprakash.xfullstack.features.users.controllers.info

import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.users.resource.UsersResource
import io.ktor.server.application.ApplicationCall

interface UsersInfoController {
    suspend fun getUserInfo(
        call: ApplicationCall,
        resource: UsersResource.Info,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
        chatDataSource: ChatDataSource,
    )
}