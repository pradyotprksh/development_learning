package domain.repositories.chat

import core.models.response.ChatResponse
import core.models.response.ClientResponse
import core.models.response.MessageResponse
import core.models.response.XFullStackResponse
import core.parser.parseToChatResponse
import core.parser.parseToMessageResponse
import domain.services.chat.ChatDBService
import domain.services.chat.ChatRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

class ChatRepositoryImplementation(
    private val chatRemoteService: ChatRemoteService,
    private val chatDBService: ChatDBService,
) : ChatRepository {
    override suspend fun updateMessage(chatId: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = chatRemoteService.getMessages(chatId)
                if (response.status == XFullStackResponseStatus.Success) {
                    response.data?.chat?.let { chatDBService.saveChatDetails(listOf(it)) }
                    response.data?.messages?.let { chatDBService.saveMessages(it) }

                    emit(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = response.status,
                                message = response.message,
                                code = response.code,
                                data = null,
                            )
                        )
                    )
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code ?: DEFAULT_ERROR_CODE,
                        )
                    )
                }
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = DEFAULT_ERROR_CODE,
                    ),
                )
            }
            emit(ClientResponse.Idle)
        }

    override suspend fun updateChats(): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = chatRemoteService.getAllChats()
                if (response.status == XFullStackResponseStatus.Success) {
                    response.data?.let { chatDBService.saveChatDetails(it) }
                    emit(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = response.status,
                                message = response.message,
                                code = response.code,
                                data = null,
                            )
                        )
                    )
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code ?: DEFAULT_ERROR_CODE,
                        )
                    )
                }
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = DEFAULT_ERROR_CODE,
                    ),
                )
            }
            emit(ClientResponse.Idle)
        }

    override suspend fun allChatsChanges(): Flow<List<ChatResponse>> {
        return chatDBService.getChats()
            .map { dbResult -> dbResult.list.map { it.parseToChatResponse() } }
    }

    override suspend fun allMessagesChanges(chatId: String): Flow<List<MessageResponse>> {
        return chatDBService.getAllMessages(chatId)
            .map { dbResult -> dbResult.list.map { it.parseToMessageResponse() } }
    }

    override suspend fun getChatChanges(chatId: String): Flow<ChatResponse?> {
        return chatDBService.getChatChanges(chatId)
            .map { dbResult -> dbResult.obj?.parseToChatResponse() }
    }
}