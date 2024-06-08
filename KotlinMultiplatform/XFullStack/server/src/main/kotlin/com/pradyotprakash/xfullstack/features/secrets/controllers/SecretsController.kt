package com.pradyotprakash.xfullstack.features.secrets.controllers

import com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch.SecretsFetchController

class SecretsController(
    private val secretsFetchController: SecretsFetchController,
) : SecretsFetchController by secretsFetchController