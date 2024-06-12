package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.tweet.data.PollChoices
import com.pradyotprakash.xfullstack.data.tweet.data.PollVoterDetails
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import com.pradyotprakash.xfullstack.features.websockets.Connections
import core.exception.DBWriteError
import core.exception.InvalidTweet
import core.exception.UserDetailsNotFound
import core.models.request.TweetRequest
import core.models.response.XFullStackResponse
import domain.repositories.gemini.GeminiRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import org.bson.types.ObjectId
import utils.Constants
import utils.Constants.Keys.USER_ID
import utils.Constants.SuccessCode.TWEETS_DELETED_SUCCESS_CODE
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus
import kotlin.random.Random

class TweetCreateUpdateControllerImplementation(
    private val geminiRepository: GeminiRepository,
) : TweetCreateUpdateController {
    override suspend fun createTweet(
        call: ApplicationCall, userDataSource: UserDataSource, tweetDataSource: TweetDataSource,
    ) {
        val tweetsRequest = call.receive<List<TweetRequest>>()

        val principal = call.principal<JWTPrincipal>()
        val createdBy =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(createdBy) ?: throw UserDetailsNotFound()

        var parentTweetId: ObjectId? = null

        val tweetRequestFilter = tweetsRequest.filter { tweetRequest ->
            val requestParentTweetId = tweetRequest.parentTweetId

            val deletedTweetId = if (tweetRequest.isLikedTweet && requestParentTweetId != null) {
                tweetDataSource.isTweetAlreadyLiked(requestParentTweetId, createdBy)
            } else if (tweetRequest.isRepostTweet && requestParentTweetId != null) {
                tweetDataSource.isTweetAlreadyReposted(requestParentTweetId, createdBy)
            } else {
                null
            }

            if (deletedTweetId != null) {
                Connections.sendMessageToAll(
                    "$TWEETS_DELETED_SUCCESS_CODE $deletedTweetId",
                )

                false
            } else {
                true
            }
        }

        val tweetsDb = tweetRequestFilter.map { tweetRequest ->
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

            val emotions = tweetRequest.tweet?.let {
                if (it.isNotBlank()) {
                    geminiRepository.getTweetEmotion(
                        it, if (Random.nextBoolean()) {
                            System.getenv(Constants.Keys.GEMINI_API_KEY_1)
                        } else {
                            System.getenv(Constants.Keys.GEMINI_API_KEY_2)
                        }
                    )
                } else {
                    emptyList()
                }
            } ?: emptyList()

            val tweetDb = Tweet(
                tweet = tweetRequest.tweet ?: "",
                createdBy = ObjectId(createdBy),
                tweetedOn = UtilsMethod.Dates.getCurrentTimeStamp(),
                media = tweetRequest.media,
                gif = tweetRequest.gif,
                isAPoll = tweetRequest.isAPoll,
                pollChoices = tweetRequest.pollChoices.map {
                    PollChoices(
                        choice = it, voteCount = 0, voterDetails = emptyList()
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
                parentTweetId = tweetRequest.parentTweetId?.let { ObjectId(tweetRequest.parentTweetId) }
                    ?: parentTweetId,
                isQuoteTweet = tweetRequest.isQuoteTweet,
                isRepostTweet = tweetRequest.isRepostTweet,
                isLikedTweet = tweetRequest.isLikedTweet,
                emotions = emotions,
            )

            parentTweetId = tweetDb.id

            tweetDb
        }

        if (tweetsDb.isNotEmpty()) {
            val wasAcknowledged = tweetDataSource.insertNewTweets(tweetsDb)

            if (!wasAcknowledged) {
                throw DBWriteError()
            }
        }

        call.respond(
            HttpStatusCode.Created, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.TWEET_CREATED_SUCCESSFULLY,
                data = null,
            )
        )
    }

    override suspend fun voteOnTweet(
        call: ApplicationCall,
        resource: TweetResource.TweetVote,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val userId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        val tweetDetails = tweetDataSource.findTweetById(resource.tweetId) ?: throw InvalidTweet(
            message = Localization.VALID_TWEET_NOT_FOUND,
        )

        if (!tweetDetails.isAPoll) {
            throw InvalidTweet(
                message = Localization.VALID_TWEET_NOT_FOUND,
            )
        }

        tweetDetails.pollLength?.let {
            if (!UtilsMethod.Dates.isFutureTimeStamp(it)) throw InvalidTweet(
                message = Localization.POLL_VOTE_EXPIRED,
            )
        } ?: throw InvalidTweet(
            message = Localization.VALID_TWEET_NOT_FOUND,
        )

        val isUserAlreadyVoted = tweetDetails.pollChoices.find { pollChoice ->
            pollChoice.voterDetails.map { voter -> voter.votedBy }.contains(ObjectId(userId))
        } != null

        if (isUserAlreadyVoted) {
            throw InvalidTweet(
                message = Localization.VOTE_ALREADY_CASTED,
            )
        }

        val requiredOption = tweetDetails.pollChoices.find { it.id == ObjectId(resource.optionId) }

        if (requiredOption == null) {
            throw InvalidTweet(
                message = Localization.VALID_TWEET_NOT_FOUND,
            )
        }

        val updatedChoices = tweetDetails.pollChoices.map {
            if (it.id == ObjectId(resource.optionId)) {
                it.copy(
                    voteCount = it.voteCount.plus(1),
                    voterDetails = it.voterDetails + PollVoterDetails(
                        votedBy = ObjectId(userId),
                        votedOn = UtilsMethod.Dates.getCurrentTimeStamp(),
                    ),
                )
            } else {
                it
            }
        }

        val wasAcknowledged = tweetDataSource.incrementVotesOnPoll(
            tweetId = resource.tweetId,
            choices = updatedChoices,
        )

        if (!wasAcknowledged) {
            throw DBWriteError()
        }

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.TWEET_VOTE_CASTED_SUCCESSFULLY,
                data = null,
            )
        )
    }
}