package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreation

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.InvalidTweet
import core.exception.UserDetailsNotFound
import data.request.TweetRequest
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.ConstValues.TWEET_MAX_LENGTH
import utils.Localization

class TweetCreationControllerImplementation : TweetCreationController {
    override suspend fun createTweet(
        call: ApplicationCall, userDataSource: UserDataSource, tweetDataSource: TweetDataSource
    ) {
        delay(API_RESPONSE_DELAY)

        val tweetRequest = call.receive<TweetRequest>()

        userDataSource.getUserByUserId(tweetRequest.createdBy) ?: throw UserDetailsNotFound()

        if (!tweetRequest.tweetIsOptional) {
            tweetRequest.tweet?.let {
                if (it.length > TWEET_MAX_LENGTH) {
                    throw InvalidTweet(
                        message = Localization.INVALID_TWEET_LENGTH
                    )
                }
            } ?: throw InvalidTweet(
                message = Localization.INVALID_TWEET_LENGTH
            )
        }
    }
}