package domain.repositories.grok

import core.models.realm.GrokMessageDB
import core.models.request.GrokRequest
import core.models.response.ClientResponse
import core.models.response.GrokChatResponse
import core.parser.parseToGrokChatResponse
import core.parser.parseToGrokConversation
import domain.services.grok.GrokDBService
import domain.services.grok.GrokRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import utils.Constants.ConstValues.MODEL
import utils.Constants.ConstValues.USER
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class GrokRepositoryImplementation(
    private val grokDBService: GrokDBService,
    private val grokRemoteService: GrokRemoteService,
) : GrokRepository {
    override suspend fun allGrokChangesChanges(): Flow<List<GrokChatResponse>> {
        return grokDBService.getAllGrokChats()
            .map { dbResult -> dbResult.list.map { db -> db.parseToGrokChatResponse() } }
    }

    override suspend fun sendPrompt(
        prompt: String,
        chatId: String,
    ): Flow<ClientResponse<out String>> = flow {
        emit(ClientResponse.Loading)
        try {
            val currentChatId = chatId.ifBlank {
                val id = BsonObjectId().toHexString()
                grokDBService.createChat(
                    chatId = id,
                    chatTitle = prompt,
                    createdOn = UtilsMethod.Dates.getCurrentTimeStamp(),
                )
                id
            }

            updateGrokConversation(
                currentChatId,
                prompt,
                USER,
            )

            emit(ClientResponse.Success(currentChatId))

            val conversations =
                grokDBService.getAllConversation(currentChatId).map { it.parseToGrokConversation() }
                    .asReversed()
            val response = grokRemoteService.sendPrompt(
                grokRequest = GrokRequest(
                    prompt = prompt,
                    chatId = currentChatId,
                    grokConversation = if (conversations.size == 1) emptyList() else conversations,
                )
            )
            if (response.status == XFullStackResponseStatus.Success) {
                val result = response.data?.response ?: ""
                if (result.isNotBlank()) {
                    updateGrokConversation(
                        currentChatId,
                        result,
                        MODEL,
                    )
                }
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = response.code ?: DEFAULT_ERROR_CODE,
                    ),
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

    private suspend fun updateGrokConversation(
        chatId: String,
        prompt: String,
        role: String,
    ) {
        val messageDB = GrokMessageDB().apply {
            this.id = BsonObjectId().toHexString()
            this.chatId = chatId
            this.message = prompt
            this.role = role
            this.messageOn = UtilsMethod.Dates.getCurrentTimeStamp()
        }
        grokDBService.updateConversation(chatId, messageDB)
    }
}