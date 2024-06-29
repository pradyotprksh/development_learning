package domain.services.chat

import core.models.request.MessageRequest
import core.models.response.ChatResponse
import core.models.response.FetchMessageResponse
import core.models.response.SendMessageResponse
import core.models.response.XFullStackResponse

interface ChatRemoteService {
    suspend fun sendMessage(messageRequest: MessageRequest): XFullStackResponse<SendMessageResponse>

    suspend fun getMessages(chatId: String): XFullStackResponse<FetchMessageResponse>

    suspend fun getAllChats(): XFullStackResponse<List<ChatResponse>>
}