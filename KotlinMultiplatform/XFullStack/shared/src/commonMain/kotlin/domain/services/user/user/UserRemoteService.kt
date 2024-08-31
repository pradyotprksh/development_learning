package domain.services.user.user

import core.models.response.UserInfoResponse
import core.models.response.XFullStackResponse

interface UserRemoteService {
    suspend fun getUserInfo(
        userId: String,
    ): XFullStackResponse<UserInfoResponse>
}