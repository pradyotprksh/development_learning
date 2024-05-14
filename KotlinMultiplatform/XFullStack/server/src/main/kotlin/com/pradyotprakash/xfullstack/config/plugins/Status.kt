package com.pradyotprakash.xfullstack.config.plugins

import com.pradyotprakash.xfullstack.data.response.ErrorResponse
import com.pradyotprakash.xfullstack.data.response.XFullStackResponse
import core.exception.InvalidParameter
import core.exception.UnauthorizedAccess
import core.exception.UserAuthDetailsError
import core.exception.UserDetailsNotFound
import core.exception.XFullStackException
import core.utils.Localization
import core.utils.ResponseStatus
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.Unauthorized) { call, _ ->
            call.respond(
                HttpStatusCode.Unauthorized,
                XFullStackResponse(
                    status = ResponseStatus.Error,
                    data = ErrorResponse(
                        message = Localization.UNAUTHORIZED_ACCESS,
                    )
                )
            )
        }

        exception<XFullStackException> { call, cause ->
            when (cause) {
                is InvalidParameter -> call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = ResponseStatus.Error,
                        data = ErrorResponse(
                            message = cause.message,
                        )
                    )
                )

                is UnauthorizedAccess -> call.respond(
                    HttpStatusCode.Unauthorized,
                    XFullStackResponse(
                        status = ResponseStatus.Error,
                        data = ErrorResponse(
                            message = cause.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        )
                    )
                )

                is UserAuthDetailsError, UserDetailsNotFound -> call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = ResponseStatus.Error,
                        data = ErrorResponse(
                            message = cause.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        )
                    )
                )
            }
        }
    }
}