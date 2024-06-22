package com.pradyotprakash.xfullstack.features.chat.controllers

import com.pradyotprakash.xfullstack.features.chat.controllers.fetchMessages.FetchMessagesController
import com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage.SendMessageController

class ChatController(
    private val sendMessageController: SendMessageController,
    private val fetchMessagesController: FetchMessagesController,
) : SendMessageController by sendMessageController,
    FetchMessagesController by fetchMessagesController