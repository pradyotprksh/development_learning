package domain.services.grok

import core.models.request.GrokRequest
import core.models.response.GrokResponse
import core.models.response.XFullStackResponse

interface GrokRemoteService {
    suspend fun sendPrompt(grokRequest: GrokRequest): XFullStackResponse<GrokResponse>
}