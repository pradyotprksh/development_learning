package domain.services.user.current

import data.request.LoginRequest
import data.request.RegisterRequest
import data.response.AuthenticationResponse
import data.response.XFullStackResponse

interface CurrentUserRemoteService {
    suspend fun authenticateUser(): XFullStackResponse<Nothing>

    suspend fun registerUser(registerRequest: RegisterRequest): XFullStackResponse<Nothing>

    suspend fun loginUser(loginRequest: LoginRequest): XFullStackResponse<AuthenticationResponse>
}