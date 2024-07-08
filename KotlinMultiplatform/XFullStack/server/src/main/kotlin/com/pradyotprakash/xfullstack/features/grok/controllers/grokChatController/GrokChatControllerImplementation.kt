package com.pradyotprakash.xfullstack.features.grok.controllers.grokChatController

import com.pradyotprakash.xfullstack.core.helpers.GeminiHelper
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.grok.resource.GrokResource
import core.exception.InvalidParameter
import core.exception.UserDetailsNotFound
import core.models.request.GrokRequest
import core.models.response.GrokResponse
import core.models.response.XFullStackResponse
import domain.repositories.gemini.GeminiRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import utils.Constants.ConstValues.MODEL
import utils.Constants.ConstValues.USER
import utils.Constants.ErrorCode.INVALID_ROLE_FOR_GROK_ERROR_CODE
import utils.Constants.GeminiPrompt.GROK_PROMPT
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class GrokChatControllerImplementation(
    private val geminiRepository: GeminiRepository,
    private val geminiHelper: GeminiHelper,
) : GrokChatController {
    override suspend fun replyToPrompt(
        call: ApplicationCall,
        resource: GrokResource.Chat,
        userDataSource: UserDataSource,
    ) {
        val grokRequest = call.receive<GrokRequest>()

        grokRequest.grokConversation.forEach {
            if (!listOf(USER, MODEL).contains(it.role)) {
                throw InvalidParameter(
                    errorCode = INVALID_ROLE_FOR_GROK_ERROR_CODE, message = Localization.format(
                        Localization.INVALID_ROLE,
                        it.role,
                    )
                )
            }
        }

        val principal = call.principal<JWTPrincipal>()
        val userId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        val userDetails = userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        val prompt = Localization.format(
            GROK_PROMPT,
            userDetails.name,
            userDetails.username,
            UtilsMethod.Dates.convertLongToReadableDate(userDetails.dateOfBirth),
            userDetails.nature.joinToString(", "),
            grokRequest.prompt,
        )

        val grokReply = geminiRepository.getGrokReply(
            prompt,
            grokRequest.grokConversation,
            geminiHelper.getGeminiAPIKey(),
        )

        call.respond(
            HttpStatusCode.Created, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.SUCCESS,
                data = GrokResponse(
                    chatId = grokRequest.chatId,
                    response = grokReply.firstOrNull() ?: "",
                    tweetResponse = emptyList(),
                    chatResponse = emptyList(),
                    messageResponse = emptyList(),
                    userInfoResponse = emptyList(),
                ),
            )
        )
    }
}