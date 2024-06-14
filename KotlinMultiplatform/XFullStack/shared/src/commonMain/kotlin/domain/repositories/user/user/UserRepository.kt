package domain.repositories.user.user

import core.models.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserInfoChanges(id: String): Flow<UserInfoResponse?>
}