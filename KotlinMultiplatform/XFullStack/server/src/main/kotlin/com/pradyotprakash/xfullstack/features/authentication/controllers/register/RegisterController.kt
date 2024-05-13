package com.pradyotprakash.xfullstack.features.authentication.controllers.register

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.server.application.ApplicationCall

interface RegisterController {
    suspend fun registerUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Register,
        hashingService: HashingService,
        userDataSource: UserDataSource,
    )
}