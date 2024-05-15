package domain.services

import data.models.realm.CurrentUserId

interface UserLocalDBService {
    fun getCurrentUserId(): CurrentUserId?

    fun getToken(userId: String): String
}