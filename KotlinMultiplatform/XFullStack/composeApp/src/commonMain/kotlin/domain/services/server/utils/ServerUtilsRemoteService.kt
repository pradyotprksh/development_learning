package domain.services.server.utils

import data.response.XFullStackResponse

interface ServerUtilsRemoteService {
    suspend fun isServerAvailable(): XFullStackResponse<Nothing>
}