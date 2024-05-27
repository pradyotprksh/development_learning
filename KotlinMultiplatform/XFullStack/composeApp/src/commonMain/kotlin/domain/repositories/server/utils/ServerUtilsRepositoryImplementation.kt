package domain.repositories.server.utils

import core.models.response.ClientResponse
import data.response.XFullStackResponse
import domain.services.server.utils.ServerUtilsRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants
import utils.Localization
import utils.XFullStackResponseStatus

class ServerUtilsRepositoryImplementation(
    private val serverUtilsRemoteService: ServerUtilsRemoteService,
) : ServerUtilsRepository {
    override suspend fun isServerAvailable(): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = serverUtilsRemoteService.isServerAvailable()
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
                        ),
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