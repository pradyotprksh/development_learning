package com.pradyotprakash.xfullstack.features.utils.controllers

import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidController

class UtilsController(
    private val usernameValidController: UsernameValidController,
) : UsernameValidController by usernameValidController