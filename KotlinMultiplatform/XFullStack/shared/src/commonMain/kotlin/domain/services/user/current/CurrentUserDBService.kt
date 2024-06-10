package domain.services.user.current

import core.models.realm.CurrentUserIdDB
import core.models.realm.CurrentUserInfoDB
import core.models.realm.TokenDB
import core.models.response.UserInfoResponse
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface CurrentUserDBService {
    fun getUserId(): CurrentUserIdDB?

    fun getToken(userId: String): TokenDB?

    suspend fun saveTokenDetails(userId: String, token: String): Boolean

    suspend fun saveUserId(userId: String): Boolean

    suspend fun deleteDetails()

    suspend fun saveUserInfo(userInfoResponse: UserInfoResponse): CurrentUserInfoDB

    suspend fun getUserInfo(userId: String): Flow<ResultsChange<CurrentUserInfoDB>>
}