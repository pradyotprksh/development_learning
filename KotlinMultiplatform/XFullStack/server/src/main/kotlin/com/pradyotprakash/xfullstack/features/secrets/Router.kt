package com.pradyotprakash.xfullstack.features.secrets

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.secrets.controllers.SecretsController
import com.pradyotprakash.xfullstack.features.secrets.resource.SecretsResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.secrets(
    secretsController: SecretsController,
    userDataSource: UserDataSource,
) {
    authenticate {
        get<SecretsResource> {
            secretsController.getSecrets(
                this.context,
                it,
                userDataSource,
            )
        }
    }
}