package domain.repositories.user.follow

import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import domain.services.user.follow.UserFollowRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants
import utils.Localization
import utils.XFullStackResponseStatus

class UserFollowRepositoryImplementation(
    private val userFollowRemoteService: UserFollowRemoteService,
) : UserFollowRepository {
    override suspend fun followUpdateStatus(followingId: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userFollowRemoteService.updateFollowStatus(followingId)
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
                        )
                    )
                }
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = Constants.ErrorCode.DEFAULT_ERROR_CODE,
                    ),
                )
            }
            emit(ClientResponse.Idle)
        }
}