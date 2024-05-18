package domain.repositories.user.current

import core.models.response.ClientResponse
import data.request.LoginRequest
import data.request.RegisterRequest
import data.response.AuthenticationResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    fun getUserId(): String?

    fun getToken(userId: String): String?

    suspend fun saveUserDetails(userId: String, token: String)

    suspend fun authenticateUser(): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun deleteUserDetails(fromLocal: Boolean, fromRemote: Boolean)

    suspend fun registerUser(registerRequest: RegisterRequest): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun loginUser(loginRequest: LoginRequest): Flow<ClientResponse<out XFullStackResponse<AuthenticationResponse>>>
}