package com.pradyotprakash.xfullstack.features.tweet.controllers

import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate.TweetCreateUpdateController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch.TweetFetchController

class TweetController(
    private val tweetCreationController: TweetCreateUpdateController,
    private val tweetFetchController: TweetFetchController,
) : TweetCreateUpdateController by tweetCreationController,
    TweetFetchController by tweetFetchController