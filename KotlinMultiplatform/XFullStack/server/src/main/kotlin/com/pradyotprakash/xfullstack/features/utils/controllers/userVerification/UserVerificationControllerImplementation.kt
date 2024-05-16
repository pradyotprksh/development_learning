package com.pradyotprakash.xfullstack.features.utils.controllers.userVerification

import com.pradyotprakash.xfullstack.data.request.OtpVerificationRequest
import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import core.utils.Constants.ConstValues.OTP_LENGTH
import core.utils.Constants.ErrorCode.OTP_GENERATION_ERROR_CODE
import core.utils.Constants.ErrorCode.OTP_VALIDATION_ERROR_CODE
import core.utils.Localization
import core.utils.ResponseStatus
import core.utils.UtilsMethod
import data.response.DefaultResponse
import data.response.OTPResponse
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class UserVerificationControllerImplementation : UserVerificationController {
    override suspend fun generateOtp(call: ApplicationCall, resource: UtilsResource.GenerateOtp) {
        try {
            val otp = UtilsMethod.getIntegerValue(
                value = resource.value,
                length = OTP_LENGTH,
            )
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = ResponseStatus.Success,
                    errorCode = null,
                    data = OTPResponse(
                        otp = otp,
                    )
                )
            )
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                XFullStackResponse(
                    status = ResponseStatus.Error,
                    errorCode = OTP_GENERATION_ERROR_CODE,
                    data = DefaultResponse(
                        message = Localization.OTP_GENERATION_ERROR
                    )
                )
            )
        }
    }

    override suspend fun validateOtp(call: ApplicationCall, resource: UtilsResource.ValidateOtp) {
        val otpVerificationRequest = call.receive<OtpVerificationRequest>()

        try {
            val otp = UtilsMethod.getIntegerValue(
                value = otpVerificationRequest.value,
                length = OTP_LENGTH,
            )

            if (otp == otpVerificationRequest.otp) {
                call.respond(
                    HttpStatusCode.OK,
                    XFullStackResponse(
                        status = ResponseStatus.Success,
                        errorCode = null,
                        data = DefaultResponse(
                            message = Localization.OTP_VERIFICATION_SUCCESS
                        )
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = ResponseStatus.Error,
                        errorCode = OTP_VALIDATION_ERROR_CODE,
                        data = DefaultResponse(
                            message = Localization.OTP_VERIFICATION_INVALID
                        )
                    )
                )
            }
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                XFullStackResponse(
                    status = ResponseStatus.Error,
                    errorCode = OTP_VALIDATION_ERROR_CODE,
                    data = DefaultResponse(
                        message = Localization.OTP_VERIFICATION_ERROR
                    )
                )
            )
        }
    }
}