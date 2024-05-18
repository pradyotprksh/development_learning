package domain.services.user.current

import core.models.realm.CurrentUserId
import core.models.realm.Token

interface CurrentUserDBService {
    fun getUserId(): CurrentUserId?

    fun getToken(userId: String): Token?

    suspend fun saveTokenDetails(userId: String, token: String): Boolean

    suspend fun saveUserId(userId: String): Boolean

    suspend fun deleteDetails()
}