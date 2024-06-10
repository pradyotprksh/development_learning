package domain.repositories.server.utils

import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface ServerUtilsRepository {
    suspend fun isServerAvailable(): Flow<ClientResponse<out XFullStackResponse<Nothing>>>
}