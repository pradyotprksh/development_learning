package data.remote.user.verification

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.request.OtpVerificationRequest
import data.response.OTPResponse
import data.response.XFullStackResponse
import domain.services.user.verification.UserVerificationRemoteService
import utils.Constants.Keys.VALUE
import utils.Constants.Paths.Utils.USERNAME_VALID
import utils.Constants.Paths.Utils.UTILS
import utils.Constants.Paths.Verification.GENERATE_OTP
import utils.Constants.Paths.Verification.USER_PRESENT
import utils.Constants.Paths.Verification.VALIDATE_OTP
import utils.Constants.Paths.Verification.VERIFICATION

class UserVerificationRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : UserVerificationRemoteService {
    override suspend fun isUserPresent(value: String): XFullStackResponse<DefaultResponse> {
        val response = networkClient.get<XFullStackResponse<DefaultResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "${VERIFICATION}${USER_PRESENT}",
                queries = mapOf(
                    VALUE to value
                )
            )
        )

        return response.getOrThrow()
    }

    override suspend fun generateOtp(value: String): XFullStackResponse<OTPResponse> {
        val response = networkClient.get<XFullStackResponse<OTPResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "${VERIFICATION}${GENERATE_OTP}",
                queries = mapOf(
                    VALUE to value,
                )
            )
        )

        return response.getOrThrow()
    }

    override suspend fun validateOtp(
        otpVerificationRequest: OtpVerificationRequest
    ): XFullStackResponse<DefaultResponse> {
        val response = networkClient.post<XFullStackResponse<DefaultResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "${VERIFICATION}${VALIDATE_OTP}",
                body = otpVerificationRequest,
            )
        )

        return response.getOrThrow()
    }

    override suspend fun isUserNameValid(username: String): XFullStackResponse<DefaultResponse> {
        val response = networkClient.get<XFullStackResponse<DefaultResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "${UTILS}${USERNAME_VALID}",
                queries = mapOf(
                    VALUE to username,
                )
            )
        )

        return response.getOrThrow()
    }
}