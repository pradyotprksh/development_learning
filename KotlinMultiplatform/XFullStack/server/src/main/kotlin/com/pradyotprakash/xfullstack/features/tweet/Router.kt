package com.pradyotprakash.xfullstack.features.tweet

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.tags.TagsDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.tweet.controllers.TweetController
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.tweet(
    tweetController: TweetController,
    tweetDataSource: TweetDataSource,
    userDataSource: UserDataSource,
    viewDataSource: ViewDataSource,
    followDataSource: FollowDataSource,
    bookmarkDataSource: BookmarkDataSource,
    tagsDataSource: TagsDataSource,
    chatDataSource: ChatDataSource,
) {
    authenticate {
        get<TweetResource.TweetPaginateResource> {
            tweetController.getAllTweets(
                this.context,
                it,
                userDataSource,
                tweetDataSource,
                viewDataSource,
                followDataSource,
                bookmarkDataSource,
                chatDataSource,
            )
        }

        post<TweetResource> {
            tweetController.createTweet(
                this.context,
                userDataSource,
                tweetDataSource,
                tagsDataSource,
            )
        }

        post<TweetResource.TweetVoteResource> {
            tweetController.voteOnTweet(
                this.context,
                it,
                userDataSource,
                tweetDataSource,
            )
        }
    }
}