package com.pradyotprakash.xfullstack.features.users.controllers

import com.pradyotprakash.xfullstack.features.users.controllers.info.UsersInfoController

class UsersController(
    private val usersInfoController: UsersInfoController,
) : UsersInfoController by usersInfoController