package com.pradyotprakash.xfullstack.features.tweet.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Tweets.PAGINATE
import utils.Constants.Paths.Tweets.TWEET
import utils.Constants.Paths.Tweets.TWEET_VOTE

@Resource(TWEET)
class TweetResource {
    @Resource(PAGINATE)
    data class TweetPaginateResource(
        private val parent: TweetResource = TweetResource(),
        val page: Int = 1,
        val limit: Int = 10,
    )

    @Resource(TWEET_VOTE)
    data class TweetVoteResource(
        private val parent: TweetResource = TweetResource(),
        val tweetId: String,
        val optionId: String,
    )
}