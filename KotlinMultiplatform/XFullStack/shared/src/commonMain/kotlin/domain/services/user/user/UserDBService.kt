package domain.services.user.user

import core.models.realm.UserInfoDB
import core.models.response.UserInfoResponse
import io.realm.kotlin.notifications.SingleQueryChange
import kotlinx.coroutines.flow.Flow

interface UserDBService {
    fun getUserInfoChanges(id: String): Flow<SingleQueryChange<UserInfoDB>>

    suspend fun saveUserInfo(userInfoResponse: UserInfoResponse)
}