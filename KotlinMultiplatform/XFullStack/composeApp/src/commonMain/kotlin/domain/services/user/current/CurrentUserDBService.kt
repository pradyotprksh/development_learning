package domain.services.user.current

import core.models.realm.CurrentUserId

interface CurrentUserDBService {
    fun getUserId(): CurrentUserId?

    fun getToken(userId: String): String

    suspend fun deleteDetails()
}