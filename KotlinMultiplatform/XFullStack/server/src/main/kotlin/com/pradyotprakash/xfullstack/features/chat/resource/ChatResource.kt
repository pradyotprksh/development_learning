package com.pradyotprakash.xfullstack.features.chat.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Chat.CHAT
import utils.Constants.Paths.Chat.SEND_MESSAGE

@Resource(CHAT)
class ChatResource {

    @Resource(SEND_MESSAGE)
    data class SendMessage(
        private val parent: ChatResource = ChatResource(),
    )
}