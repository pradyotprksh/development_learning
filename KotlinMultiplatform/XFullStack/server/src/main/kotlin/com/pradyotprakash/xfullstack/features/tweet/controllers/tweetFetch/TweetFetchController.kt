package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import io.ktor.server.application.ApplicationCall

interface TweetFetchController {
    suspend fun getAllTweets(
        call: ApplicationCall,
        tweetPaginate: TweetResource.TweetPaginate,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
    )
}