package domain.services

import data.models.realm.CurrentUserId

interface UserDBService {
    fun getCurrentUserId(): CurrentUserId?

    fun getToken(userId: String): String
}