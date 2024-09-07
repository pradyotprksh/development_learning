package com.pradyotprakash.xfullstack.features.tags

import com.pradyotprakash.xfullstack.data.tags.TagsDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.tags.controllers.TagsController
import com.pradyotprakash.xfullstack.features.tags.resource.TagsResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.tags(
    tagsController: TagsController,
    userDataSource: UserDataSource,
    tweetDataSource: TweetDataSource,
    tagsDataSource: TagsDataSource,
) {
    authenticate {
        get<TagsResource.TrendingResource> {
            tagsController.getTrendingTags(
                this.context, userDataSource, tweetDataSource, tagsDataSource
            )
        }
    }
}