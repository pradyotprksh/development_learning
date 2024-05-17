package domain.services.user.current

import data.request.RegisterRequest
import data.response.XFullStackResponse

interface CurrentUserRemoteService {
    suspend fun authenticateUser(): XFullStackResponse<Nothing>

    suspend fun registerUser(registerRequest: RegisterRequest): XFullStackResponse<Nothing>
}