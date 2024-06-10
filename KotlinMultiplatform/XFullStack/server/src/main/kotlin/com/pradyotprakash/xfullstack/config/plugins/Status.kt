package com.pradyotprakash.xfullstack.config.plugins

import core.exception.DBWriteError
import core.exception.InvalidParameter
import core.exception.InvalidTweet
import core.exception.UnauthorizedAccess
import core.exception.UserAuthDetailsError
import core.exception.UserDetailsNotFound
import core.exception.XFullStackException
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import utils.Constants.ErrorCode.BAD_REQUEST_ERROR_CODE
import utils.Constants.ErrorCode.INTERNAL_SERVER_ERROR_CODE
import utils.Constants.ErrorCode.UNAUTHORIZED_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.Unauthorized) { call, _ ->
            call.respond(
                HttpStatusCode.Unauthorized,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Error,
                    code = UNAUTHORIZED_ERROR_CODE,
                    message = Localization.UNAUTHORIZED_ACCESS,
                    data = null
                )
            )
        }

        status(HttpStatusCode.BadRequest) { call, _ ->
            call.respond(
                HttpStatusCode.BadRequest,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Error,
                    code = BAD_REQUEST_ERROR_CODE,
                    message = HttpStatusCode.BadRequest.description,
                    data = null
                )
            )
        }

        status(HttpStatusCode.InternalServerError) { call, _ ->
            call.respond(
                HttpStatusCode.InternalServerError,
                XFullStackResponse(
                    status = XFullStackResponseStatus.Error,
                    code = INTERNAL_SERVER_ERROR_CODE,
                    message = HttpStatusCode.InternalServerError.description,
                    data = null,
                )
            )
        }

        exception<XFullStackException> { call, cause ->
            when (cause) {
                is InvalidParameter -> call.respond(
                    HttpStatusCode.BadRequest,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = cause.errorCode,
                        message = cause.message,
                        data = null
                    )
                )

                is UnauthorizedAccess -> call.respond(
                    HttpStatusCode.Unauthorized,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = cause.errorCode,
                        message = cause.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        data = null
                    )
                )

                is UserAuthDetailsError -> call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = cause.errorCode,
                        message = cause.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        data = null
                    )
                )

                is UserDetailsNotFound -> call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = cause.errorCode,
                        message = cause.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        data = null
                    )
                )

                is DBWriteError -> call.respond(
                    HttpStatusCode.BadGateway,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = cause.errorCode,
                        message = cause.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        data = null
                    )
                )

                is InvalidTweet -> call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = XFullStackResponseStatus.Error,
                        code = cause.errorCode,
                        message = cause.message,
                        data = null
                    )
                )
            }
        }
    }
}