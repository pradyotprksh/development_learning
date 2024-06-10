package domain.services.user.current

import core.models.request.LoginRequest
import core.models.request.RegisterRequest
import core.models.response.AuthenticationResponse
import core.models.response.UserInfoResponse
import core.models.response.XFullStackResponse

interface CurrentUserRemoteService {
    suspend fun authenticateUser(): XFullStackResponse<Nothing>

    suspend fun registerUser(registerRequest: RegisterRequest): XFullStackResponse<Nothing>

    suspend fun loginUser(loginRequest: LoginRequest): XFullStackResponse<AuthenticationResponse>

    suspend fun getUserInfo(): XFullStackResponse<UserInfoResponse>
}