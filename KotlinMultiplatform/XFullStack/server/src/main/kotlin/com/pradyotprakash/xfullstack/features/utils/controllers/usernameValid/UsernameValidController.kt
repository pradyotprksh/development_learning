package com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import io.ktor.server.application.ApplicationCall

interface UsernameValidController {
    fun isUserNameValid(
        call: ApplicationCall,
        resource: UtilsResource.UsernameValid,
        userDataSource: UserDataSource,
    )
}