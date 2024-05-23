package com.pradyotprakash.xfullstack.features.tweet.controllers

import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreation.TweetCreationController

class TweetController(
    private val tweetCreationController: TweetCreationController,
) : TweetCreationController by tweetCreationController