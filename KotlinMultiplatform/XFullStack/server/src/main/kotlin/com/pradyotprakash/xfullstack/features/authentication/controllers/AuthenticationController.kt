package com.pradyotprakash.xfullstack.features.authentication.controllers

import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterController

class AuthenticationController(
    private val registerController: RegisterController
) : RegisterController by registerController