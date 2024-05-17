package domain.services.user.current

import data.request.RegisterRequest
import data.response.XFullStackResponse

interface CurrentUserRemoteService {
    suspend fun authenticateUser(): XFullStackResponse<DefaultResponse>

    suspend fun registerUser(registerRequest: RegisterRequest): XFullStackResponse<DefaultResponse>
}