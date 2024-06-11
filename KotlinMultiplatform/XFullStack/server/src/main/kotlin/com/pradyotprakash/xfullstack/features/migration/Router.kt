package com.pradyotprakash.xfullstack.features.migration

import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import utils.Constants.Paths.Migration.MIGRATION
import utils.Constants.SuccessCode.DB_MIGRATION_SUCCESS_CODE
import utils.Localization
import utils.XFullStackResponseStatus

fun Routing.migration() {
    post(MIGRATION) {
        // DONE: tweetDataSource.addNewFieldToAll(EMOTIONS, emptyList<String>())
        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = DB_MIGRATION_SUCCESS_CODE,
                message = Localization.DB_MIGRATION_SUCCESS,
                data = null
            )
        )
    }
}