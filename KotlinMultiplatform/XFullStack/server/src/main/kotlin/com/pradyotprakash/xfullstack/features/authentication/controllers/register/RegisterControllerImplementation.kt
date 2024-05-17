package com.pradyotprakash.xfullstack.features.authentication.controllers.register

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.User
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import core.exception.DBWriteError
import core.exception.InvalidParameter
import data.request.RegisterRequest
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import utils.Constants.ErrorCode.EMAIL_ALREADY_PRESENT_ERROR_CODE
import utils.Constants.ErrorCode.EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE
import utils.Constants.ErrorCode.PHONE_NUMBER_ALREADY_PRESENT_ERROR_CODE
import utils.Constants.ErrorCode.PROFILE_PICTURE_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.USERNAME_ALREADY_PRESENT_ERROR_CODE
import utils.Localization
import utils.ResponseStatus
import utils.UtilsMethod

class RegisterControllerImplementation : RegisterController {
    override suspend fun registerUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Register,
        hashingService: HashingService,
        userDataSource: UserDataSource
    ) {
        val registerRequest = call.receive<RegisterRequest>()

        UtilsMethod.isValidName(registerRequest.name)

        if (UtilsMethod.isValidUserName(registerRequest.username)) {
            if (userDataSource.isUsernamePresent(registerRequest.username)) {
                throw InvalidParameter(
                    errorCode = USERNAME_ALREADY_PRESENT_ERROR_CODE,
                    message = Localization.USERNAME_ALREADY_EXISTS,
                )
            }
        }

        UtilsMethod.isValidPassword(registerRequest.password)

        if (registerRequest.emailAddress == null && registerRequest.phoneNumber == null) {
            throw InvalidParameter(
                message = Localization.EMAIL_OR_PHONE_NUMBER_REQUIRED,
                errorCode = EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE
            )
        }

        registerRequest.emailAddress?.let {
            if (UtilsMethod.isValidEmail(it)) {
                if (userDataSource.isEmailPresent(it)) {
                    throw InvalidParameter(
                        message = Localization.EMAIL_ALREADY_EXISTS,
                        errorCode = EMAIL_ALREADY_PRESENT_ERROR_CODE
                    )
                }
            }
        }

        registerRequest.phoneNumber?.let {
            if (UtilsMethod.isValidPhoneNumber(it)) {
                if (userDataSource.isPhoneNumberPresent(it)) {
                    throw InvalidParameter(
                        message = Localization.PHONE_NUMBER_ALREADY_EXISTS,
                        errorCode = PHONE_NUMBER_ALREADY_PRESENT_ERROR_CODE
                    )
                }
            }
        }

        registerRequest.profilePicture?.let {
            UtilsMethod.isValidLink(
                it,
                PROFILE_PICTURE_VALIDITY_ERROR_CODE
            )
        }
        UtilsMethod.isValidDate(registerRequest.dateOfBirth)

        val saltedHash = hashingService.generateSaltedHash(value = registerRequest.password)

        val user = User(
            name = registerRequest.name,
            username = registerRequest.username,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            bio = registerRequest.bio,
            profilePicture = registerRequest.profilePicture,
            dateOfBirth = registerRequest.dateOfBirth,
            emailAddress = registerRequest.emailAddress,
            phoneNumber = registerRequest.phoneNumber,
        )

        val wasAcknowledged = userDataSource.insertNewUser(user)

        if (!wasAcknowledged) {
            throw DBWriteError()
        }

        call.respond(
            HttpStatusCode.Created,
            XFullStackResponse(
                status = ResponseStatus.Success,
                errorCode = null,
                message = Localization.ACCOUNT_CREATED_SUCCESSFULLY,
                data = null,
            )
        )
    }
}