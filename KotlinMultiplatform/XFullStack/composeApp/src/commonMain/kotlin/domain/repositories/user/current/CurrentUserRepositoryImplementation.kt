package domain.repositories.user.current

import core.models.response.ClientResponse
import data.request.RegisterRequest
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization.DEFAULT_ERROR_MESSAGE
import utils.ResponseStatus

class CurrentUserRepositoryImplementation(
    private val currentUserDBService: CurrentUserDBService,
    private val currentUserRemoteService: CurrentUserRemoteService,
) : CurrentUserRepository {
    override fun getUserId(): String? {
        return currentUserDBService.getUserId()?.userId
    }

    override fun getToken(userId: String): String {
        return currentUserDBService.getToken(userId)
    }

    override suspend fun authenticateUser(): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = currentUserRemoteService.authenticateUser()
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: DEFAULT_ERROR_MESSAGE,
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

    override suspend fun registerUser(registerRequest: RegisterRequest): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = currentUserRemoteService.registerUser(registerRequest)
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: DEFAULT_ERROR_MESSAGE,
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
            currentUserDBService.deleteDetails()
        }
    }
}