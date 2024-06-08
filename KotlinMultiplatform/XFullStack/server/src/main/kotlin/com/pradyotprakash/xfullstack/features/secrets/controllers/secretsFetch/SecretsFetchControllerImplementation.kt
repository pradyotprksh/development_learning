package com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.secrets.resource.SecretsResource
import core.exception.InvalidParameter
import core.exception.UserDetailsNotFound
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import utils.Constants.ErrorCode.INVALID_REQUEST_ASK
import utils.Constants.Keys.GEMINI_API_KEY
import utils.Constants.Keys.GEMINI_API_KEY_1
import utils.Constants.Keys.GEMINI_API_KEY_2
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.XFullStackResponseStatus
import kotlin.random.Random

class SecretsFetchControllerImplementation : SecretsFetchController {
    override suspend fun getSecrets(
        call: ApplicationCall, resource: SecretsResource, userDataSource: UserDataSource
    ) {
        val principal = call.principal<JWTPrincipal>()
        val createdBy =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(createdBy) ?: throw UserDetailsNotFound()

        val requestKey = resource.type

        when (requestKey) {
            GEMINI_API_KEY -> {
                call.respond(
                    HttpStatusCode.OK, XFullStackResponse(
                        status = XFullStackResponseStatus.Success,
                        code = null,
                        message = Localization.SUCCESS,
                        data = if (Random.nextBoolean()) {
                            System.getenv(GEMINI_API_KEY_1)
                        } else {
                            System.getenv(GEMINI_API_KEY_2)
                        }
                    )
                )
            }

            else -> {
                throw InvalidParameter(
                    message = Localization.ASKED_SECRET_NOT_PRESENT, errorCode = INVALID_REQUEST_ASK
                )
            }
        }
    }
}