package com.pradyotprakash.xfullstack.features.migration

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
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
    userDataSource: UserDataSource,
) {
    post(MIGRATION) {
        // DONE: tweetDataSource.addNewFieldToAll(EMOTIONS, emptyList<String>())
        // DONE: tweetDataSource.removeKeyFromAll(RETWEET_COUNT)
        // DONE: tweetDataSource.removeKeyFromAll(COMMENT_COUNT)
        // DONE: userDataSource.removeKeyFromAll("followers")
        // DONE: userDataSource.removeKeyFromAll("following")
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