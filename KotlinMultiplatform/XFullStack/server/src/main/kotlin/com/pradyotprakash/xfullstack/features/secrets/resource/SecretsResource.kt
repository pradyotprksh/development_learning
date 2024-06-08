package com.pradyotprakash.xfullstack.features.secrets.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Secrets.SECRETS

@Resource(SECRETS)
class SecretsResource(
    val type: String,
)