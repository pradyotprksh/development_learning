package com.pradyotprakash.xfullstack.features.utils.controllers

import com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails.ServerDetailsController
import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidController

class UtilsController(
    private val usernameValidController: UsernameValidController,
    private val serverDetailsController: ServerDetailsController,
) : UsernameValidController by usernameValidController,
    ServerDetailsController by serverDetailsController