package domain.repositories.user.current

import core.models.response.ClientResponse
import data.response.DefaultResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    fun getUserId(): String?

    fun getToken(userId: String): String

    suspend fun authenticateUser(): Flow<ClientResponse<out XFullStackResponse<DefaultResponse>>>

    suspend fun deleteUserDetails(fromLocal: Boolean, fromRemote: Boolean)
}