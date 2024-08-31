package domain.repositories.user.user

import core.models.response.UserInfoResponse
import core.parser.parseToCurrentUserResponse
import domain.services.user.user.UserDBService
import domain.services.user.user.UserRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImplementation(
    private val userDBService: UserDBService,
    private val userRemoteService: UserRemoteService,
) : UserRepository {
    override suspend fun getUserInfoChanges(id: String): Flow<UserInfoResponse?> {
        return userDBService.getUserInfoChanges(id).map { dbUser ->
            dbUser.obj?.parseToCurrentUserResponse() ?: run {
                userRemoteService.getUserInfo(id).data?.let { remoteUser ->
                    userDBService.saveUserInfo(remoteUser)
                }
                null
            }
        }
    }
}