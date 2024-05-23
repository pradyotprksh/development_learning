package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreation

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.tweet.data.PollChoices
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.DBWriteError
import core.exception.InvalidTweet
import core.exception.UserDetailsNotFound
import data.request.TweetRequest
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import org.bson.types.ObjectId
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.ResponseStatus
import utils.UtilsMethod

class TweetCreationControllerImplementation : TweetCreationController {
    override suspend fun createTweet(
        call: ApplicationCall, userDataSource: UserDataSource, tweetDataSource: TweetDataSource
    ) {
        delay(API_RESPONSE_DELAY)

        val tweetsRequest = call.receive<List<TweetRequest>>()

        val principal = call.principal<JWTPrincipal>()
        val createdBy =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(createdBy) ?: throw UserDetailsNotFound()

        val tweetsDb = tweetsRequest.map { tweetRequest ->
            if (!tweetRequest.tweetIsOptional) {
                UtilsMethod.Validation.isTweetValid(tweetRequest.tweet)
            }

            if (tweetRequest.isAPoll) {
                UtilsMethod.Validation.isPollTweetValid(
                    tweetRequest.tweet,
                    tweetRequest.pollChoices,
                    tweetRequest.pollHour,
                )
            }

            if (tweetRequest.parentTweetIdIsRequired) {
                tweetRequest.parentTweetId?.let {
                    tweetDataSource.findTweetById(it) ?: throw InvalidTweet(
                        message = Localization.PARENT_TWEET_NOT_FOUND,
                    )
                } ?: throw InvalidTweet(
                    message = Localization.PARENT_TWEET_NOT_FOUND,
                )
            }

            if (tweetRequest.isScheduledTweet) {
                tweetRequest.scheduledOnTweet?.let {
                    if (!UtilsMethod.Dates.isFutureTimeStamp(it)) {
                        throw InvalidTweet(
                            message = Localization.SCHEDULED_TWEET_IS_NOT_CORRECT,
                        )
                    }
                } ?: throw InvalidTweet(
                    message = Localization.SCHEDULED_TWEET_IS_NOT_CORRECT,
                )
            }

            Tweet(
                tweet = tweetRequest.tweet ?: "",
                createdBy = ObjectId(createdBy),
                tweetedOn = UtilsMethod.Dates.getCurrentTimeStamp(),
                media = tweetRequest.media,
                gif = tweetRequest.gif,
                commentCount = 0,
                retweetCount = 0,
                likesCount = 0,
                views = 0,
                isAPoll = tweetRequest.isAPoll,
                pollChoices = tweetRequest.pollChoices.map {
                    PollChoices(
                        choice = it,
                        voteCount = 0,
                    )
                },
                pollLength = if (tweetRequest.isAPoll) UtilsMethod.Dates.getFutureTimeStamp(
                    tweetRequest.pollHour ?: 0,
                    tweetRequest.pollMinute,
                    tweetRequest.pollSeconds,
                ) else 0,
                scheduledOnTweet = tweetRequest.scheduledOnTweet ?: 0,
                location = tweetRequest.location ?: "",
                isACommentTweet = tweetRequest.isACommentTweet,
                parentTweetId = tweetRequest.parentTweetId?.let { ObjectId(tweetRequest.parentTweetId) },
                isQuoteTweet = tweetRequest.isQuoteTweet,
                isRepostTweet = tweetRequest.isRepostTweet,
                isLikedTweet = tweetRequest.isLikedTweet,
            )
        }

        val wasAcknowledged = tweetDataSource.insertNewTweets(tweetsDb)

        if (!wasAcknowledged) {
            throw DBWriteError()
        }

        call.respond(
            HttpStatusCode.Created,
            XFullStackResponse(
                status = ResponseStatus.Success,
                code = null,
                message = Localization.TWEET_CREATED_SUCCESSFULLY,
                data = null,
            )
        )
    }
}