package domain.services.user.verification

import data.request.OtpVerificationRequest
import data.response.OTPResponse
import data.response.XFullStackResponse

interface UserVerificationRemoteService {
    suspend fun isUserPresent(value: String): XFullStackResponse<Nothing>

    suspend fun generateOtp(value: String): XFullStackResponse<OTPResponse>

    suspend fun validateOtp(otpVerificationRequest: OtpVerificationRequest): XFullStackResponse<Nothing>

    suspend fun isUserNameValid(username: String): XFullStackResponse<Nothing>
}