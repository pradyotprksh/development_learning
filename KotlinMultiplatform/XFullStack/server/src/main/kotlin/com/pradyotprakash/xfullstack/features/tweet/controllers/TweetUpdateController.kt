package com.pradyotprakash.xfullstack.features.tweet.controllers

import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreation.TweetCreateUpdateController

class TweetUpdateController(
    private val tweetCreationController: TweetCreateUpdateController,
) : TweetCreateUpdateController by tweetCreationController