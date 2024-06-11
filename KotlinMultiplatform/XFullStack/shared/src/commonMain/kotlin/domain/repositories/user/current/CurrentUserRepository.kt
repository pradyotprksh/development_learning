package domain.repositories.user.current

import core.models.request.LoginRequest
import core.models.request.RegisterRequest
import core.models.response.AuthenticationResponse
import core.models.response.ClientResponse
import core.models.response.UserInfoResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    fun getUserId(): String?

    fun getToken(userId: String): String?

    suspend fun saveUserDetails(userId: String, token: String): Boolean

    suspend fun authenticateUser(): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun deleteUserDetails(fromLocal: Boolean, fromRemote: Boolean)

    suspend fun registerUser(registerRequest: RegisterRequest): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun loginUser(loginRequest: LoginRequest): Flow<ClientResponse<out XFullStackResponse<AuthenticationResponse>>>

    suspend fun updateUserInfo(): Flow<ClientResponse<out XFullStackResponse<UserInfoResponse>>>

    suspend fun userInfoChanges(userId: String): Flow<UserInfoResponse?>

    suspend fun updateScrollPosition(key: String, scrollPosition: Int)

    fun getScrollPosition(key: String): Int
}