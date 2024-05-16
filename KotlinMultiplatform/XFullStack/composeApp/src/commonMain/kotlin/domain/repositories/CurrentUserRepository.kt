package domain.repositories

import domain.services.UserDBService

class CurrentUserRepository(
    private val userDBService: UserDBService,
) {
    fun getCurrentUserId(): String? {
        return userDBService.getCurrentUserId()?.userId
    }

    fun getToken(userId: String): String {
        return userDBService.getToken(userId)
    }
}