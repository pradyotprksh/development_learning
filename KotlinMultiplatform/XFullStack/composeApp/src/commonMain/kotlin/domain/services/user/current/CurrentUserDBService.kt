package domain.services.user.current

import core.models.realm.CurrentUserId
import core.models.realm.Token

interface CurrentUserDBService {
    fun getUserId(): CurrentUserId?

    fun getToken(userId: String): Token?

    suspend fun saveTokenDetails(token: Token)

    suspend fun saveUserId(userId: CurrentUserId)

    suspend fun deleteDetails()
}