package domain.services.server.utils

import core.models.response.XFullStackResponse

interface ServerUtilsRemoteService {
    suspend fun isServerAvailable(): XFullStackResponse<Nothing>
}