package com.pradyotprakash.xfullstack.features.bookmark.controllers.bookmarkUpdate

import com.pradyotprakash.xfullstack.data.bookmark.Bookmark
import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.bookmark.resource.BookmarkResource
import com.pradyotprakash.xfullstack.features.websockets.Connections
import core.exception.DBWriteError
import core.exception.InvalidTweet
import core.exception.UserDetailsNotFound
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import org.bson.types.ObjectId
import utils.Constants.Keys.USER_ID
import utils.Constants.SuccessCode.TWEETS_UPDATE_SUCCESS_CODE
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class BookmarkUpdateControllerImplementation : BookmarkUpdateController {
    override suspend fun updateFollower(
        call: ApplicationCall,
        resource: BookmarkResource.Update,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        bookmarkDataSource: BookmarkDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val userId = principal?.payload?.getClaim(USER_ID)?.asString()
            ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        tweetDataSource.findTweetById(resource.tweetId) ?: throw InvalidTweet(
            message = Localization.TWEET_NOT_FOUND,
        )

        if (bookmarkDataSource.removeBookmark(
                tweetId = resource.tweetId, userId = userId,
            )
        ) {
            Connections.sendMessageToAll(TWEETS_UPDATE_SUCCESS_CODE)

            call.respond(
                HttpStatusCode.OK, XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = null,
                    message = Localization.BOOKMARK_REMOVED_SUCCESSFULLY,
                    data = null,
                )
            )
        } else {
            val wasAcknowledged = bookmarkDataSource.addBookmark(
                bookmark = Bookmark(
                    tweetId = ObjectId(resource.tweetId),
                    userId = ObjectId(userId),
                    bookmarkedAt = UtilsMethod.Dates.getCurrentTimeStamp(),
                )
            )

            if (!wasAcknowledged) {
                throw DBWriteError()
            }

            Connections.sendMessageToAll(TWEETS_UPDATE_SUCCESS_CODE)

            call.respond(
                HttpStatusCode.Created, XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = null,
                    message = Localization.BOOKMARK_ADDED_SUCCESSFULLY,
                    data = null,
                )
            )
        }
    }
}