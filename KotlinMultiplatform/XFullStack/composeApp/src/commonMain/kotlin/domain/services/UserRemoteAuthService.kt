package domain.services

import data.response.DefaultResponse
import data.response.XFullStackResponse

interface UserRemoteAuthService {
    suspend fun authenticateCurrentUser(): XFullStackResponse<DefaultResponse?>
}