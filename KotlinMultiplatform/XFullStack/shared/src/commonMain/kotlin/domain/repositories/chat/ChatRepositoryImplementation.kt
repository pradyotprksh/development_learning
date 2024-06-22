package domain.repositories.chat

import core.models.response.ChatResponse
import core.models.response.ClientResponse
import core.models.response.FetchMessageResponse
import core.models.response.XFullStackResponse
import domain.services.chat.ChatRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

class ChatRepositoryImplementation(
    private val chatRemoteService: ChatRemoteService,
) : ChatRepository {
    override suspend fun getMessages(chatId: String): Flow<ClientResponse<out XFullStackResponse<FetchMessageResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = chatRemoteService.getMessages(chatId)
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = response.status,
                                message = response.message,
                                code = response.code,
                                data = response.data,
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

    override suspend fun getChats(): Flow<ClientResponse<out XFullStackResponse<List<ChatResponse>>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = chatRemoteService.getAllChats()
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = response.status,
                                message = response.message,
                                code = response.code,
                                data = response.data,
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
}