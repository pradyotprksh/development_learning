package com.pradyotprakash.xfullstack.features.chat.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Chat.CHAT
import utils.Constants.Paths.Chat.GET_MESSAGES
import utils.Constants.Paths.Chat.SEND_MESSAGE

@Resource(CHAT)
class ChatResource {

    @Resource(SEND_MESSAGE)
    data class SendMessage(
        private val parent: ChatResource = ChatResource(),
    )

    @Resource(GET_MESSAGES)
    data class GetMessages(
        private val parent: ChatResource = ChatResource(),
        val chatId: String,
    )
}