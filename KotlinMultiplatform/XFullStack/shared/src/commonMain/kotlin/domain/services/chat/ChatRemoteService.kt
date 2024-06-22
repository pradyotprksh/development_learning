package domain.services.chat

import core.models.request.MessageRequest
import core.models.response.FetchMessageResponse
import core.models.response.XFullStackResponse

interface ChatRemoteService {
    suspend fun sendMessage(messageRequest: MessageRequest): XFullStackResponse<Nothing>

    suspend fun getMessages(chatId: String): XFullStackResponse<FetchMessageResponse>
}