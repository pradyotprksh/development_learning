package com.pradyotprakash.xfullstack.features.verification.controllers

import com.pradyotprakash.xfullstack.features.verification.controllers.userVerification.UserVerificationController

class VerificationController(
    private val userVerificationController: UserVerificationController,
) : UserVerificationController by userVerificationController