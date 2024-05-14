package com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.server.application.ApplicationCall

interface UserInfoController {
    suspend fun getUserInfo(
        call: ApplicationCall,
        resource: AuthenticationResource.UserInfo,
        userDataSource: UserDataSource,
    )
}