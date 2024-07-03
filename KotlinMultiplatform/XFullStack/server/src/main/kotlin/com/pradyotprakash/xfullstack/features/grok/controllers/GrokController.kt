package com.pradyotprakash.xfullstack.features.grok.controllers

import com.pradyotprakash.xfullstack.features.grok.controllers.grokChatController.GrokChatController

class GrokController(
    private val grokChatController: GrokChatController,
) : GrokChatController by grokChatController