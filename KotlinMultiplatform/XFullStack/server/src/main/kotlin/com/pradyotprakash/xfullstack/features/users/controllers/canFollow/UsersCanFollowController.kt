package com.pradyotprakash.xfullstack.features.users.controllers.canFollow

import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface UsersCanFollowController {
    suspend fun getUserCanBeFollowed(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
        chatDataSource: ChatDataSource,
    )
}