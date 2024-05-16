package com.pradyotprakash.xfullstack.features.utils.controllers

import com.pradyotprakash.xfullstack.features.utils.controllers.userVerification.UserVerificationController
import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidController

class UtilsController(
    private val usernameValidController: UsernameValidController,
    private val userVerificationController: UserVerificationController,
) : UsernameValidController by usernameValidController,
    UserVerificationController by userVerificationController