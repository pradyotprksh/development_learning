package domain.repositories.user.current

import core.models.realm.CurrentUserInfoDB
import core.models.response.ClientResponse
import data.request.LoginRequest
import data.request.RegisterRequest
import data.response.AuthenticationResponse
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    override fun getToken(userId: String): String? {
        return currentUserDBService.getToken(userId)?.token
    }

    override suspend fun saveUserDetails(userId: String, token: String): Boolean {
        val isTokenSaved = currentUserDBService.saveTokenDetails(userId, token)
        val isCurrentUserIdSaved = currentUserDBService.saveUserId(userId)

        return isTokenSaved && isCurrentUserIdSaved
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
                            errorCode = response.code ?: DEFAULT_ERROR_CODE,
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
                            errorCode = response.code ?: DEFAULT_ERROR_CODE,
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

    override suspend fun loginUser(loginRequest: LoginRequest): Flow<ClientResponse<out XFullStackResponse<AuthenticationResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = currentUserRemoteService.loginUser(loginRequest)
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code ?: DEFAULT_ERROR_CODE,
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

    override suspend fun updateUserInfo(): Flow<ClientResponse<out XFullStackResponse<CurrentUserInfoDB>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = currentUserRemoteService.getUserInfo()
                if (response.status == ResponseStatus.Success) {
                    response.data?.let { userInfoResponse ->
                        val dbSavedData = currentUserDBService.saveUserInfo(userInfoResponse)
                        emit(
                            ClientResponse.Success(
                                XFullStackResponse(
                                    status = response.status,
                                    message = response.message,
                                    code = response.code,
                                    data = dbSavedData,
                                )
                            )
                        )
                    } ?: run {
                        emit(
                            ClientResponse.Error(
                                message = DEFAULT_ERROR_MESSAGE,
                                errorCode = DEFAULT_ERROR_CODE,
                            ),
                        )
                    }
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code ?: DEFAULT_ERROR_CODE,
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

    override suspend fun userInfoChanges(userId: String): Flow<CurrentUserInfoDB?> =
        currentUserDBService.getUserInfo(userId).map { it.list.firstOrNull() }

    override suspend fun deleteUserDetails(fromLocal: Boolean, fromRemote: Boolean) {
        if (fromLocal) {
            currentUserDBService.deleteDetails()
        }
    }
}