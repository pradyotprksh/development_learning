package app.pages.directMessage.state

import core.models.response.MessageResponse

data class MessageDetails(
    val group: String? = null,
    val messageResponse: MessageResponse? = null,
)
