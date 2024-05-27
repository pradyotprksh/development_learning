package com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import core.exception.InvalidParameter
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import utils.Constants.ErrorCode.USERNAME_ALREADY_PRESENT_ERROR_CODE
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class UsernameValidControllerImplementation : UsernameValidController {
    override suspend fun isUserNameValid(
        call: ApplicationCall, resource: UtilsResource.UsernameValid, userDataSource: UserDataSource
    ) {
        val username = resource.value

        if (UtilsMethod.Validation.isValidUserName(username)) {
            if (userDataSource.isUsernamePresent(username)) {
                throw InvalidParameter(
                    errorCode = USERNAME_ALREADY_PRESENT_ERROR_CODE,
                    message = Localization.USERNAME_ALREADY_EXISTS
                )
            }
        }

        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.VALID_USERNAME,
                data = null,
            )
        )
    }
}