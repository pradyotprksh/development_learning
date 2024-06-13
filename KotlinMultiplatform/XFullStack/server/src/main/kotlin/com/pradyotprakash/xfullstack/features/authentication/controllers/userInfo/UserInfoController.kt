package com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo

import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface UserInfoController {
    suspend fun getUserInfo(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
    )
}