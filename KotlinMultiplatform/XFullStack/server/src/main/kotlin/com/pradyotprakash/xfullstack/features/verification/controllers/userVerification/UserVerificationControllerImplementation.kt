package com.pradyotprakash.xfullstack.features.verification.controllers.userVerification

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.verification.resource.VerificationResource
import data.request.OtpVerificationRequest
import data.response.DefaultResponse
import data.response.OTPResponse
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.OTP_LENGTH
import utils.Constants.ErrorCode.OTP_GENERATION_ERROR_CODE
import utils.Constants.ErrorCode.OTP_VALIDATION_ERROR_CODE
import utils.Constants.ErrorCode.USER_DETAILS_NOT_FOUND_CODE
import utils.Localization
import utils.Logger
import utils.LoggerLevel
import utils.ResponseStatus
import utils.UtilsMethod

class UserVerificationControllerImplementation : UserVerificationController {
    override suspend fun generateOtp(
        call: ApplicationCall,
        resource: VerificationResource.GenerateOtp
    ) {
        delay(1500)
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
                        message = Localization.OTP_GENERATE_SUCCESSFULLY,
                    )
                )
            )
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                XFullStackResponse(
                    status = ResponseStatus.Error,
                    errorCode = OTP_GENERATION_ERROR_CODE,
                    data = OTPResponse(
                        otp = "",
                        message = Localization.OTP_GENERATION_ERROR
                    )
                )
            )
        }
    }

    override suspend fun validateOtp(
        call: ApplicationCall,
        resource: VerificationResource.ValidateOtp
    ) {
        delay(1500)
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

    override suspend fun userPresent(
        call: ApplicationCall,
        resource: VerificationResource.UserPresent,
        userDataSource: UserDataSource
    ) {
        Logger.log(LoggerLevel.Info, "UserPresent: ${resource.value}")
        if (userDataSource.isUsernamePresent(resource.value)) {
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = ResponseStatus.Success,
                    errorCode = null,
                    data = DefaultResponse(
                        message = Localization.USER_PRESENT
                    )
                )
            )
            return
        }
        Logger.log(LoggerLevel.Info, "User Name Not Present: ${resource.value}")

        if (userDataSource.isEmailPresent(resource.value)) {
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = ResponseStatus.Success,
                    errorCode = null,
                    data = DefaultResponse(
                        message = Localization.USER_PRESENT
                    )
                )
            )
            return
        }
        Logger.log(LoggerLevel.Info, "User Email Not Present: ${resource.value}")

        if (userDataSource.isPhoneNumberPresent(resource.value)) {
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = ResponseStatus.Success,
                    errorCode = null,
                    data = DefaultResponse(
                        message = Localization.USER_PRESENT
                    )
                )
            )
            return
        }
        Logger.log(LoggerLevel.Info, "User Phone Number Not Present: ${resource.value}")

        call.respond(
            HttpStatusCode.Conflict,
            XFullStackResponse(
                status = ResponseStatus.Error,
                errorCode = USER_DETAILS_NOT_FOUND_CODE,
                data = DefaultResponse(
                    message = Localization.USER_DETAILS_NOT_FOUND
                )
            )
        )
    }
}