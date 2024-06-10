package domain.services.user.verification

import core.models.request.OtpVerificationRequest
import core.models.response.OTPResponse
import core.models.response.XFullStackResponse

interface UserVerificationRemoteService {
    suspend fun isUserPresent(value: String): XFullStackResponse<Nothing>

    suspend fun generateOtp(value: String): XFullStackResponse<OTPResponse>

    suspend fun validateOtp(otpVerificationRequest: OtpVerificationRequest): XFullStackResponse<Nothing>

    suspend fun isUserNameValid(username: String): XFullStackResponse<Nothing>
}