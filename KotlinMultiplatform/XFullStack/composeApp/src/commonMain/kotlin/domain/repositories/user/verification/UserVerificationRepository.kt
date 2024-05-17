package domain.repositories.user.verification

import core.models.response.ClientResponse
import data.response.DefaultResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface UserVerificationRepository {
    suspend fun isUserPresent(value: String): Flow<ClientResponse<out XFullStackResponse<DefaultResponse>>>
}