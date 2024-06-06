package com.pradyotprakash.xfullstack.features.view.controllers

import com.pradyotprakash.xfullstack.features.view.controllers.create.ViewCreateController

class ViewController(
    private val viewCreateController: ViewCreateController,
) : ViewCreateController by viewCreateController