package data.remote.chat

import core.models.request.MessageRequest
import core.models.request.XFullStackClientRequestDetails
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.chat.ChatRemoteService
import utils.Constants.Paths.Chat.CHAT
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
}