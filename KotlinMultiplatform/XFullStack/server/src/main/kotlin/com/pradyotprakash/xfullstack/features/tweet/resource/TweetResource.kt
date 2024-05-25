package com.pradyotprakash.xfullstack.features.tweet.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Tweets.TWEET
import utils.Constants.Paths.Tweets.TWEET_VOTE

@Resource(TWEET)
class TweetResource {

    @Resource(TWEET_VOTE)
    data class TweetVote(
        private val parent: TweetResource = TweetResource(),
        val tweetId: String,
        val optionId: String,
    )
}