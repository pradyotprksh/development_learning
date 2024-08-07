package domain.repositories.user.verification

import core.models.request.OtpVerificationRequest
import core.models.response.ClientResponse
import core.models.response.OTPResponse
import core.models.response.XFullStackResponse
import domain.services.user.verification.UserVerificationRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants
import utils.Localization
import utils.XFullStackResponseStatus

class UserVerificationRepositoryImplementation(
    private val userVerificationRemoteService: UserVerificationRemoteService,
) : UserVerificationRepository {
    override suspend fun isUserPresent(value: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userVerificationRemoteService.isUserPresent(value)
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code
                                ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
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

    override suspend fun generateOtp(value: String): Flow<ClientResponse<out XFullStackResponse<OTPResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userVerificationRemoteService.generateOtp(value)
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code
                                ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
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

    override suspend fun validateOtp(
        value: String,
        otp: String,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userVerificationRemoteService.validateOtp(
                    OtpVerificationRequest(
                        value = value,
                        otp = otp,
                    )
                )
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code
                                ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
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

    override suspend fun isUsernameValid(value: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userVerificationRemoteService.isUserNameValid(username = value)
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code
                                ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
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