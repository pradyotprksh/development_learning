package domain.services.user.verification

import data.request.OtpVerificationRequest
import data.response.DefaultResponse
import data.response.OTPResponse
import data.response.XFullStackResponse

interface UserVerificationRemoteService {
    suspend fun isUserPresent(value: String): XFullStackResponse<DefaultResponse>

    suspend fun generateOtp(value: String): XFullStackResponse<OTPResponse>

    suspend fun validateOtp(otpVerificationRequest: OtpVerificationRequest): XFullStackResponse<DefaultResponse>

    suspend fun isUserNameValid(username: String): XFullStackResponse<DefaultResponse>
}