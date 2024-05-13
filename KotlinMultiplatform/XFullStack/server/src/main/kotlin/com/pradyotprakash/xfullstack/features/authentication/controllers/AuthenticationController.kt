package com.pradyotprakash.xfullstack.features.authentication.controllers

import com.pradyotprakash.xfullstack.features.authentication.controllers.login.LoginController
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterController

class AuthenticationController(
    private val registerController: RegisterController,
    private val loginController: LoginController,
) : RegisterController by registerController, LoginController by loginController