package domain.repositories.user.verification

import core.models.response.ClientResponse
import data.request.OtpVerificationRequest
import data.response.DefaultResponse
import data.response.OTPResponse
import data.response.XFullStackResponse
import domain.services.user.verification.UserVerificationRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants
import utils.Localization
import utils.ResponseStatus

class UserVerificationRepositoryImplementation(
    private val userVerificationRemoteService: UserVerificationRemoteService,
) : UserVerificationRepository {
    override suspend fun isUserPresent(value: String): Flow<ClientResponse<out XFullStackResponse<DefaultResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userVerificationRemoteService.isUserPresent(value)
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.data?.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.errorCode
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
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.data?.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.errorCode
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
        otp: String
    ): Flow<ClientResponse<out XFullStackResponse<DefaultResponse>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = userVerificationRemoteService.validateOtp(
                    OtpVerificationRequest(
                        value = value,
                        otp = otp,
                    )
                )
                if (response.status == ResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.data?.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.errorCode
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