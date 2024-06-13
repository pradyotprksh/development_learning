package domain.services.user.follow

import core.models.response.XFullStackResponse

interface UserFollowRemoteService {
    suspend fun updateFollowStatus(followingId: String): XFullStackResponse<Nothing>
}