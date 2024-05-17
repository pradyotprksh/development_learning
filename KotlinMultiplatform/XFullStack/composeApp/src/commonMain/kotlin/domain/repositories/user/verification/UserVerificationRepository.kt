package domain.repositories.user.verification

import core.models.response.ClientResponse
import data.response.OTPResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface UserVerificationRepository {
    suspend fun isUserPresent(value: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun generateOtp(value: String): Flow<ClientResponse<out XFullStackResponse<OTPResponse>>>

    suspend fun validateOtp(
        value: String,
        otp: String,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun isUsernameValid(value: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>>
}