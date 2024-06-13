package com.pradyotprakash.xfullstack.features.authentication.controllers.register

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface RegisterController {
    suspend fun registerUser(
        call: ApplicationCall,
        hashingService: HashingService,
        userDataSource: UserDataSource,
    )
}