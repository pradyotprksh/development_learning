package domain.repositories

import domain.services.UserLocalDBService

class CurrentUserRepository(
    private val userLocalDBService: UserLocalDBService,
) {
    fun getCurrentLoggedInUserId(): String? {
        return userLocalDBService.getCurrentUserId()?.userId
    }

    fun getToken(userId: String): String {
        return userLocalDBService.getToken(userId)
    }
}