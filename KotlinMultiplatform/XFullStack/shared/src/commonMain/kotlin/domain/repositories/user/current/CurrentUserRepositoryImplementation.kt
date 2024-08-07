package domain.repositories.user.current

import core.models.request.LoginRequest
import core.models.request.RegisterRequest
import core.models.response.AuthenticationResponse
import core.models.response.ClientResponse
import core.models.response.UserInfoResponse
import core.models.response.XFullStackResponse
import core.parser.parseToCurrentUserResponse
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Constants.Keys.FOR_YOU_SCROLL_POSITION
import utils.Localization.DEFAULT_ERROR_MESSAGE
import utils.XFullStackResponseStatus

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
                if (response.status == XFullStackResponseStatus.Success) {
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
                if (response.status == XFullStackResponseStatus.Success) {
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
                if (response.status == XFullStackResponseStatus.Success) {
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

    override suspend fun updateUserInfo(): Flow<ClientResponse<out XFullStackResponse<UserInfoResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = currentUserRemoteService.getUserInfo()
                if (response.status == XFullStackResponseStatus.Success) {
                    response.data?.let { userInfoResponse ->
                        currentUserDBService.saveUserInfo(userInfoResponse)
                        emit(
                            ClientResponse.Success(
                                XFullStackResponse(
                                    status = response.status,
                                    message = response.message,
                                    code = response.code,
                                    data = userInfoResponse,
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

    override suspend fun userInfoChanges(userId: String): Flow<UserInfoResponse?> =
        currentUserDBService.getUserInfo(userId)
            .map { it.list.firstOrNull()?.parseToCurrentUserResponse() }

    override suspend fun updateScrollPosition(key: String, scrollPosition: Int) {
        if (listOf(FOR_YOU_SCROLL_POSITION).contains(key)) {
            currentUserDBService.updateScrollPosition(key, scrollPosition)
        }
    }

    override fun getScrollPosition(key: String): Int =
        if (listOf(FOR_YOU_SCROLL_POSITION).contains(key)) {
            currentUserDBService.getScrollPosition(key) ?: 0
        } else {
            0
        }


    override suspend fun deleteUserDetails(fromLocal: Boolean, fromRemote: Boolean) {
        if (fromLocal) {
            currentUserDBService.deleteDetails()
        }
    }
}