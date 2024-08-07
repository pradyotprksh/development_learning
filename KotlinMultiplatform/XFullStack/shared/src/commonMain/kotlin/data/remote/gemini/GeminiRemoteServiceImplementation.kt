package data.remote.gemini

import core.models.request.Content
import core.models.request.GeminiRequest
import core.models.request.GrokConversation
import core.models.request.Part
import core.models.request.XFullStackClientRequestDetails
import core.models.response.GeminiResponse
import core.network.NetworkClient
import domain.services.gemini.GeminiRemoteService
import utils.Constants.ConstValues.USER
import utils.Constants.GeminiPrompt.TWEET_EMOTION
import utils.Constants.GeminiPrompt.USER_NATURE
import utils.Constants.Keys.KEY
import utils.Constants.Paths.Gemini.BETA_1_5_MODEL_GENERATE_CONTENT
import utils.Localization

class GeminiRemoteServiceImplementation(
    private val geminiRemoteClient: NetworkClient,
) : GeminiRemoteService {
    override suspend fun getTweetEmotion(
        value: String,
        apiKey: String,
    ): GeminiResponse? {
        val prompt = Localization.format(
            TWEET_EMOTION,
            value,
        )
        val response = geminiRemoteClient.post<GeminiResponse>(
            details = XFullStackClientRequestDetails(
                endpoint = BETA_1_5_MODEL_GENERATE_CONTENT,
                queries = mapOf(
                    KEY to apiKey,
                ),
                body = GeminiRequest(
                    contents = listOf(
                        Content(
                            role = USER,
                            parts = listOf(
                                Part(
                                    text = prompt,
                                ),
                            )
                        )
                    )
                ),
            )
        )

        return response.getOrNull()
    }

    override suspend fun getHumanNature(value: String, apiKey: String): GeminiResponse? {
        val prompt = Localization.format(
            USER_NATURE,
            value,
        )
        val response = geminiRemoteClient.post<GeminiResponse>(
            details = XFullStackClientRequestDetails(
                endpoint = BETA_1_5_MODEL_GENERATE_CONTENT,
                queries = mapOf(
                    KEY to apiKey,
                ),
                body = GeminiRequest(
                    contents = listOf(
                        Content(
                            role = USER,
                            parts = listOf(
                                Part(
                                    text = prompt,
                                ),
                            )
                        )
                    )
                ),
            )
        )

        return response.getOrNull()
    }

    override suspend fun getGrokReply(
        value: String,
        pastGrokConversation: List<GrokConversation>,
        apiKey: String,
    ): GeminiResponse? {
        val response = geminiRemoteClient.post<GeminiResponse>(
            details = XFullStackClientRequestDetails(
                endpoint = BETA_1_5_MODEL_GENERATE_CONTENT,
                queries = mapOf(
                    KEY to apiKey,
                ),
                body = GeminiRequest(
                    contents = pastGrokConversation.map {
                        Content(
                            role = it.role,
                            parts = listOf(
                                Part(
                                    text = it.prompt,
                                )
                            )
                        )
                    } + listOf(
                        Content(
                            role = "user",
                            parts = listOf(
                                Part(
                                    text = value,
                                ),
                            ),
                        ),
                    ),
                ),
            ),
        )

        return response.getOrNull()
    }
}