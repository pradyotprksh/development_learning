package com.pradyotprakash.xfullstack.features.migration

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
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

fun Routing.migration(
    tweetDataSource: TweetDataSource,
) {
    post(MIGRATION) {
        // DONE: tweetDataSource.addNewFieldToAll("emotions", emptyList<String>())
        // DONE: tweetDataSource.removeKeyFromAll("retweet_count")
        // DONE: tweetDataSource.removeKeyFromAll("comment_count")
        // DONE: userDataSource.removeKeyFromAll("followers")
        // DONE: userDataSource.removeKeyFromAll("following")
        // DONE: userDataSource.addNewFieldToAll("nature", emptyList<String>())
        // DONE: tweetDataSource.addNewFieldToAll("tags", emptyList<String>())
        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = DB_MIGRATION_SUCCESS_CODE,
                message = Localization.DB_MIGRATION_SUCCESS,
                data = null
            )
        )
    }
}