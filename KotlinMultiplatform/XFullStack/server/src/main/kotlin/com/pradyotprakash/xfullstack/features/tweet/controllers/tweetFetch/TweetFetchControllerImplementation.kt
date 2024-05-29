package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch

import com.pradyotprakash.xfullstack.core.data.parseToUserInfoResponse
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import core.exception.UserDetailsNotFound
import data.response.PollChoicesResponse
import data.response.TweetsResponse
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class TweetFetchControllerImplementation : TweetFetchController {
    override suspend fun getAllTweets(
        call: ApplicationCall,
        tweetPaginate: TweetResource.TweetPaginate,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource
    ) {
        delay(API_RESPONSE_DELAY)

        val principal = call.principal<JWTPrincipal>()
        val createdBy =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(createdBy) ?: throw UserDetailsNotFound()

        val tweets = tweetDataSource.getAllTweets(tweetPaginate.page, tweetPaginate.limit)

        val tweetsResponse = tweets.map { tweet ->
            val createdByUserDetails = userDataSource.getUserByUserId(tweet.createdBy.toHexString())
                ?: throw UserDetailsNotFound()

            TweetsResponse(
                id = tweet.id.toHexString(),
                tweet = tweet.tweet,
                createdBy = createdByUserDetails.parseToUserInfoResponse(),
                tweetedOnTimestamp = tweet.tweetedOn,
                tweetedOn = UtilsMethod.Dates.convertTimestampToTimeAgo(tweet.tweetedOn),
                media = tweet.media,
                gif = tweet.gif,
                commentCount = tweet.commentCount,
                retweetCount = tweet.retweetCount,
                likesCount = tweet.likesCount,
                views = tweet.views,
                isAPoll = tweet.isAPoll,
                pollChoices = tweet.pollChoices.map { pollChoice ->
                    PollChoicesResponse(
                        id = pollChoice.id.toHexString(),
                        choice = pollChoice.choice,
                        voteCount = pollChoice.voteCount,
                    )
                },
                isPollingAllowed = UtilsMethod.Dates.isFutureTimeStamp(tweet.pollLength ?: 0),
                scheduledOnTweet = tweet.scheduledOnTweet,
                location = tweet.location,
                isACommentTweet = tweet.isACommentTweet,
                isQuoteTweet = tweet.isQuoteTweet,
                isRepostTweet = tweet.isRepostTweet,
                isLikedTweet = tweet.isLikedTweet,
                parentTweetId = tweet.parentTweetId?.toHexString(),
            )
        }.filter {
            !UtilsMethod.Dates.isFutureTimeStamp(it.scheduledOnTweet)
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