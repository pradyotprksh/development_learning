package com.pradyotprakash.xfullstack.features.verification.controllers.userVerification

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.verification.resource.VerificationResource
import data.request.OtpVerificationRequest
import data.response.OTPResponse
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.ConstValues.OTP_LENGTH
import utils.Constants.ErrorCode.OTP_GENERATION_ERROR_CODE
import utils.Constants.ErrorCode.OTP_VALIDATION_ERROR_CODE
import utils.Constants.ErrorCode.USER_DETAILS_NOT_FOUND_CODE
import utils.Constants.SuccessCode.EMAIL_PRESENT_SUCCESS_CODE
import utils.Constants.SuccessCode.PHONE_NUMBER_PRESENT_SUCCESS_CODE
import utils.Constants.SuccessCode.USERNAME_PRESENT_SUCCESS_CODE
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class UserVerificationControllerImplementation : UserVerificationController {
    override suspend fun generateOtp(
        call: ApplicationCall,
        resource: VerificationResource.GenerateOtp
    ) {
        delay(API_RESPONSE_DELAY)
        try {
            val otp = UtilsMethod.Conversion.getIntegerValue(
                value = resource.value,
                length = OTP_LENGTH,
            )
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = null,
                    message = Localization.OTP_GENERATE_SUCCESSFULLY,
                    data = OTPResponse(
                        otp = otp,
                    )
                )
            )
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Error,
                    code = OTP_GENERATION_ERROR_CODE,
                    message = Localization.OTP_GENERATION_ERROR,
                    data = null
                )
            )
        }
    }

    override suspend fun validateOtp(
        call: ApplicationCall,
        resource: VerificationResource.ValidateOtp
    ) {
        delay(API_RESPONSE_DELAY)
        val otpVerificationRequest = call.receive<OtpVerificationRequest>()

        try {
            val otp = UtilsMethod.Conversion.getIntegerValue(
                value = otpVerificationRequest.value,
                length = OTP_LENGTH,
            )

            if (otp == otpVerificationRequest.otp) {
                call.respond(
                    HttpStatusCode.OK,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Success,
                        code = null,
                        message = Localization.OTP_VERIFICATION_SUCCESS,
                        data = null
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = OTP_VALIDATION_ERROR_CODE,
                        message = Localization.OTP_VERIFICATION_INVALID,
                        data = null
                    )
                )
            }
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Error,
                    code = OTP_VALIDATION_ERROR_CODE,
                    message = Localization.OTP_VERIFICATION_ERROR,
                    data = null
                )
            )
        }
    }

    override suspend fun userPresent(
        call: ApplicationCall,
        resource: VerificationResource.UserPresent,
        userDataSource: UserDataSource
    ) {
        if (userDataSource.isUsernamePresent(resource.value)) {
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = USERNAME_PRESENT_SUCCESS_CODE,
                    message = Localization.USER_PRESENT,
                    data = null,
                )
            )
            return
        }

        if (userDataSource.isEmailPresent(resource.value)) {
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = EMAIL_PRESENT_SUCCESS_CODE,
                    message = Localization.USER_PRESENT,
                    data = null
                )
            )
            return
        }

        if (userDataSource.isPhoneNumberPresent(resource.value)) {
            call.respond(
                HttpStatusCode.OK,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = PHONE_NUMBER_PRESENT_SUCCESS_CODE,
                    message = Localization.USER_PRESENT,
                    data = null
                )
            )
            return
        }

        call.respond(
            HttpStatusCode.Conflict,
            XFullStackResponse(
                status = XFullStackResponseStatus.Error,
                code = USER_DETAILS_NOT_FOUND_CODE,
                message = Localization.USER_DETAILS_NOT_FOUND,
                data = null
            )
        )
    }
}