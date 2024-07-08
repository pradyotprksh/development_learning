package data.remote.grok

import core.models.request.GrokRequest
import core.models.request.XFullStackClientRequestDetails
import core.models.response.GrokResponse
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.grok.GrokRemoteService
import utils.Constants.Paths.Chat.CHAT
import utils.Constants.Paths.Grok.GROK

class GrokRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : GrokRemoteService {
    override suspend fun sendPrompt(
        grokRequest: GrokRequest,
    ): XFullStackResponse<GrokResponse> {
        val response = networkClient.post<XFullStackResponse<GrokResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$GROK$CHAT",
                body = grokRequest,
            )
        )

        return response.getOrThrow()
    }
}