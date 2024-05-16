package domain.repositories.user.current

import core.models.response.ClientResponse
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization.DEFAULT_ERROR_MESSAGE
import utils.ResponseStatus
import data.response.DefaultResponse
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentUserRepositoryImplementation(
    private val userDBService: CurrentUserDBService,
    private val userRemoteService: CurrentUserRemoteService,
) : CurrentUserRepository {
    override fun getUserId(): String? {
        return userDBService.getUserId()?.userId
    }

    override fun getToken(userId: String): String {
        return userDBService.getToken(userId)
    }

    override suspend fun authenticateUser(): Flow<ClientResponse<out XFullStackResponse<DefaultResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userRemoteService.authenticateUser()
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(userRemoteService.authenticateUser()))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.data?.message ?: DEFAULT_ERROR_MESSAGE,
                            errorCode = response.errorCode ?: DEFAULT_ERROR_CODE,
                        ),
                    )
                }
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: DEFAULT_ERROR_MESSAGE,
                        errorCode = DEFAULT_ERROR_CODE,
                    ),
                )
            }
            emit(ClientResponse.Idle)
        }

    override suspend fun deleteUserDetails(fromLocal: Boolean, fromRemote: Boolean) {
        if (fromLocal) {
            userDBService.deleteDetails()
        }
    }
}