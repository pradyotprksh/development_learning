package com.pradyotprakash.xfullstack.features.authentication.controllers.login

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SaltedHash
import com.pradyotprakash.xfullstack.core.security.token.TokenClaim
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import core.exception.InvalidParameter
import core.exception.UserAuthDetailsError
import core.exception.UserDetailsNotFound
import core.models.request.LoginRequest
import core.models.response.AuthenticationResponse
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.ErrorCode.USERNAME_OR_EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class LoginControllerImplementation : LoginController {
    override suspend fun loginUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Login,
        hashingService: HashingService,
        tokenService: TokenService,
        userDataSource: UserDataSource,
        tokenConfig: TokenConfig,
    ) {
        delay(API_RESPONSE_DELAY)

        val loginRequest = call.receive<LoginRequest>()

        if (loginRequest.username == null && loginRequest.emailAddress == null && loginRequest.phoneNumber == null) {
            throw InvalidParameter(
                message = Localization.USERNAME_OR_EMAIL_OR_PHONE_NUMBER_REQUIRED,
                errorCode = USERNAME_OR_EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE
            )
        }

        loginRequest.username?.let { UtilsMethod.Validation.isValidUserName(it) }
        loginRequest.emailAddress?.let { UtilsMethod.Validation.isValidEmail(it) }
        loginRequest.phoneNumber?.let { UtilsMethod.Validation.isValidPhoneNumber(it) }

        UtilsMethod.Validation.isValidPassword(loginRequest.password)

        val user =
            loginRequest.phoneNumber?.let { userDataSource.getUserByPhoneNumber(it) }
                ?: loginRequest.emailAddress?.let { userDataSource.getUserByEmailAddress(it) }
                ?: loginRequest.username?.let { userDataSource.getUserByUsername(it) }
                ?: throw UserDetailsNotFound()

        val isValidPassword = hashingService.verify(
            value = loginRequest.password, saltedHash = SaltedHash(
                hash = user.password, salt = user.salt
            )
        )
        if (!isValidPassword) {
            throw UserAuthDetailsError()
        }

        val token = tokenService.generate(
            config = tokenConfig,
            claims = arrayOf(
                TokenClaim(
                    name = USER_ID,
                    value = user.id.toHexString()
                )
            )
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.TOKEN_GENERATED_SUCCESSFULLY,
                data = AuthenticationResponse(
                    userId = user.id.toHexString(),
                    token = token
                )
            )
        )
    }
}