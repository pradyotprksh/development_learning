package com.pradyotprakash.xfullstack.features.authentication.controllers.register

import com.pradyotprakash.xfullstack.core.data.parseToUser
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.DBWriteError
import core.exception.InvalidParameter
import core.models.request.RegisterRequest
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.ErrorCode.EMAIL_ALREADY_PRESENT_ERROR_CODE
import utils.Constants.ErrorCode.EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE
import utils.Constants.ErrorCode.PHONE_NUMBER_ALREADY_PRESENT_ERROR_CODE
import utils.Constants.ErrorCode.PROFILE_PICTURE_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.USERNAME_ALREADY_PRESENT_ERROR_CODE
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class RegisterControllerImplementation : RegisterController {
    override suspend fun registerUser(
        call: ApplicationCall,
        hashingService: HashingService,
        userDataSource: UserDataSource,
    ) {
        delay(API_RESPONSE_DELAY)

        val registerRequest = call.receive<RegisterRequest>()

        UtilsMethod.Validation.isValidName(registerRequest.name)

        if (UtilsMethod.Validation.isValidUserName(registerRequest.username)) {
            if (userDataSource.isUsernamePresent(registerRequest.username)) {
                throw InvalidParameter(
                    errorCode = USERNAME_ALREADY_PRESENT_ERROR_CODE,
                    message = Localization.USERNAME_ALREADY_EXISTS,
                )
            }
        }

        UtilsMethod.Validation.isValidPassword(registerRequest.password)

        if (registerRequest.emailAddress == null && registerRequest.phoneNumber == null) {
            throw InvalidParameter(
                message = Localization.EMAIL_OR_PHONE_NUMBER_REQUIRED,
                errorCode = EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE
            )
        }

        registerRequest.emailAddress?.let {
            if (UtilsMethod.Validation.isValidEmail(it)) {
                if (userDataSource.isEmailPresent(it)) {
                    throw InvalidParameter(
                        message = Localization.EMAIL_ALREADY_EXISTS,
                        errorCode = EMAIL_ALREADY_PRESENT_ERROR_CODE
                    )
                }
            }
        }

        registerRequest.phoneNumber?.let {
            if (UtilsMethod.Validation.isValidPhoneNumber(it)) {
                if (userDataSource.isPhoneNumberPresent(it)) {
                    throw InvalidParameter(
                        message = Localization.PHONE_NUMBER_ALREADY_EXISTS,
                        errorCode = PHONE_NUMBER_ALREADY_PRESENT_ERROR_CODE
                    )
                }
            }
        }

        registerRequest.profilePicture?.let {
            UtilsMethod.Validation.isValidLink(
                it,
                PROFILE_PICTURE_VALIDITY_ERROR_CODE
            )
        }

        UtilsMethod.Validation.isValidDate(registerRequest.dateOfBirth)

        registerRequest.bio?.let { UtilsMethod.Validation.isValidBio(it) }

        val saltedHash = hashingService.generateSaltedHash(value = registerRequest.password)

        val user = registerRequest.parseToUser(saltedHash)

        val wasAcknowledged = userDataSource.insertNewUser(user)

        if (!wasAcknowledged) {
            throw DBWriteError()
        }

        call.respond(
            HttpStatusCode.Created,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.ACCOUNT_CREATED_SUCCESSFULLY,
                data = null,
            )
        )
    }
}