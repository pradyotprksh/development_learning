package domain.repositories.user.follow

import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface UserFollowRepository {
    suspend fun followUpdateStatus(followingId: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>>
}