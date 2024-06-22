package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.tweet.convertToTweetResponse
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import core.exception.UserDetailsNotFound
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.XFullStackResponseStatus

class TweetFetchControllerImplementation : TweetFetchController {
    override suspend fun getAllTweets(
        call: ApplicationCall,
        tweetPaginate: TweetResource.TweetPaginate,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
        chatDataSource: ChatDataSource,
    ) {
        delay(API_RESPONSE_DELAY)

        val principal = call.principal<JWTPrincipal>()
        val currentUserId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(currentUserId) ?: throw UserDetailsNotFound()

        val tweets = tweetDataSource.getAllTweets(tweetPaginate.page, tweetPaginate.limit)

        val tweetsResponse = tweets.map { tweet ->
            convertToTweetResponse(
                userDataSource,
                tweetDataSource,
                viewDataSource,
                followDataSource,
                bookmarkDataSource,
                chatDataSource,
                tweet,
                currentUserId
            )
        }

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.DETAILS_FOUND,
                data = tweetsResponse
            )
        )
    }
}