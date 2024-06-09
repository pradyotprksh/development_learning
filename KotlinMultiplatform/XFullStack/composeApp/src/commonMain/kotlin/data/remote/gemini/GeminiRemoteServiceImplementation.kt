package data.remote.gemini

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.request.Content
import data.request.GeminiRequest
import data.request.Part
import data.response.GeminiResponse
import domain.services.gemini.GeminiRemoteService
import utils.Constants.GeminiPrompt.TWEET_EMOTION
import utils.Constants.Keys.KEY
import utils.Constants.Paths.Gemini.BETA_1_5_MODEL_GENERATE_CONTENT
import utils.Localization

class GeminiRemoteServiceImplementation(
    private val geminiRemoteClient: NetworkClient,
    private val xFullStackRemoteClient: NetworkClient,
) : GeminiRemoteService {
    override suspend fun getTweetEmotion(
        value: String,
        apiKey: String,
    ): GeminiResponse? {
        val response = geminiRemoteClient.post<GeminiResponse>(
            details = XFullStackClientRequestDetails(
                endpoint = BETA_1_5_MODEL_GENERATE_CONTENT,
                queries = mapOf(
                    KEY to apiKey,
                ),
                body = GeminiRequest(
                    contents = listOf(
                        Content(
                            parts = listOf(
                                Part(
                                    text = Localization.format(
                                        TWEET_EMOTION,
                                        value,
                                    ),
                                ),
                            )
                        )
                    )
                ),
            )
        )

        return response.getOrNull()
    }
}