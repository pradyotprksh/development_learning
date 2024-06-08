package com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.secrets.resource.SecretsResource
import io.ktor.server.application.ApplicationCall

interface SecretsFetchController {
    suspend fun getSecrets(
        call: ApplicationCall,
        resource: SecretsResource,
        userDataSource: UserDataSource,
    )
}