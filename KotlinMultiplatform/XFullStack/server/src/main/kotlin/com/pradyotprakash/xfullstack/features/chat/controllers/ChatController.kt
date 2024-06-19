package com.pradyotprakash.xfullstack.features.chat.controllers

import com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage.SendMessageController

class ChatController(
    private val sendMessageController: SendMessageController,
) : SendMessageController by sendMessageController