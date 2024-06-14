package domain.repositories.user.user

import core.models.response.UserInfoResponse
import core.parser.parseToCurrentUserResponse
import domain.services.user.user.UserDBService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImplementation(
    private val userDBService: UserDBService,
) : UserRepository {
    override suspend fun getUserInfoChanges(id: String): Flow<UserInfoResponse?> {
        return userDBService.getUserInfoChanges(id).map { it.obj?.parseToCurrentUserResponse() }
    }
}