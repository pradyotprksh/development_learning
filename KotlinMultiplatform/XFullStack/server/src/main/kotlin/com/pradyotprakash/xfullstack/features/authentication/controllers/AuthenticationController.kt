package com.pradyotprakash.xfullstack.features.authentication.controllers

import com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate.AuthenticateController
import com.pradyotprakash.xfullstack.features.authentication.controllers.login.LoginController
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterController
import com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo.UserInfoController

class AuthenticationController(
    private val registerController: RegisterController,
    private val loginController: LoginController,
    private val authenticateController: AuthenticateController,
    private val userInfoController: UserInfoController,
) : RegisterController by registerController, LoginController by loginController,
    AuthenticateController by authenticateController, UserInfoController by userInfoController