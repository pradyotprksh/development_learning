package com.pradyotprakash.xfullstack.features.users.controllers

import com.pradyotprakash.xfullstack.features.users.controllers.canFollow.UsersCanFollowController
import com.pradyotprakash.xfullstack.features.users.controllers.info.UsersInfoController

class UsersController(
    private val usersInfoController: UsersInfoController,
    private val usersCanFollowController: UsersCanFollowController,
) : UsersInfoController by usersInfoController, UsersCanFollowController by usersCanFollowController