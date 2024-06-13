package com.pradyotprakash.xfullstack.features.follow.controllers

import com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate.FollowUpdateController

class FollowController(
    private val followUpdateController: FollowUpdateController,
) : FollowUpdateController by followUpdateController