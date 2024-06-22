package data.remote.chat

import core.models.request.MessageRequest
import core.models.request.XFullStackClientRequestDetails
import core.models.response.ChatResponse
import core.models.response.FetchMessageResponse
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.chat.ChatRemoteService
import utils.Constants.ConstValues.CHAT_ID
import utils.Constants.Paths.Chat.CHAT
import utils.Constants.Paths.Chat.GET_CHATS
import utils.Constants.Paths.Chat.GET_MESSAGES
import utils.Constants.Paths.Chat.SEND_MESSAGE

class ChatRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : ChatRemoteService {
    override suspend fun sendMessage(messageRequest: MessageRequest): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$CHAT$SEND_MESSAGE",
                body = messageRequest,
            )
        )

        return response.getOrThrow()
    }

    override suspend fun getMessages(chatId: String): XFullStackResponse<FetchMessageResponse> {
        val response = networkClient.get<XFullStackResponse<FetchMessageResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$CHAT$GET_MESSAGES", queries = mapOf(
                    CHAT_ID to chatId,
                )
            )
        )

        return response.getOrThrow()
    }

    override suspend fun getAllChats(): XFullStackResponse<List<ChatResponse>> {
        val response = networkClient.get<XFullStackResponse<List<ChatResponse>>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$CHAT$GET_CHATS",
            )
        )

        return response.getOrThrow()
    }
}