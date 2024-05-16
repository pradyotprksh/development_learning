package domain.services.user.current

import data.response.DefaultResponse
import data.response.XFullStackResponse

interface CurrentUserRemoteService {
    suspend fun authenticateUser(): XFullStackResponse<DefaultResponse>
}