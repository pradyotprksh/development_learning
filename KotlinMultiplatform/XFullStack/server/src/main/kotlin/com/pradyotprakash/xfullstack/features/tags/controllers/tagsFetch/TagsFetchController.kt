package com.pradyotprakash.xfullstack.features.tags.controllers.tagsFetch

import com.pradyotprakash.xfullstack.data.tags.TagsDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface TagsFetchController {
    suspend fun getTrendingTags(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        tagsDataSource: TagsDataSource,
    )
}