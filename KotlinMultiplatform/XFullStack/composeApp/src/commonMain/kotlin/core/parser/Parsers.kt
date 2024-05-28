package core.parser

import core.models.realm.CurrentUserInfoDB
import core.models.realm.PollChoicesDB
import core.models.realm.TweetDB
import data.response.PollChoicesResponse
import data.response.TweetsResponse
import data.response.UserInfoResponse
import io.realm.kotlin.ext.realmListOf

fun UserInfoResponse.parseToCurrentUserInfoDB() = this.let { info ->
    CurrentUserInfoDB().apply {
        this.userId = info.id
        this.name = info.name
        this.username = info.username
        this.bio = info.bio
        this.emailAddress = info.emailAddress
        this.phoneNumber = info.phoneNumber
        this.profilePicture = info.profilePicture
        this.dateOfBirth = info.dateOfBirth
        this.following = info.following
        this.followers = info.followers
    }
}

fun PollChoicesResponse.parseToPollChoicesDB() = this.let { info ->
    PollChoicesDB().apply {
        this.id = info.id
        this.choice = info.choice
        this.voteCount = info.voteCount
    }
}

fun TweetsResponse.parseToTweetsDB() = this.let { info ->
    val media = realmListOf<String>()
    info.media.forEach { media.add(it) }

    val gif = realmListOf<String>()
    info.gif.forEach { gif.add(it) }

    val pollChoicesDB = info.pollChoices.map { it.parseToPollChoicesDB() }
    val pollChoices = realmListOf<PollChoicesDB>()
    pollChoicesDB.forEach { pollChoices.add(it) }

    TweetDB().apply {
        this.tweetId = info.id
        this.tweet = info.tweet
        this.createdBy = info.createdBy.parseToCurrentUserInfoDB()
        this.tweetedOn = info.tweetedOn
        this.media = media
        this.gif = gif
        this.commentCount = info.commentCount
        this.retweetCount = info.retweetCount
        this.likeCount = info.likesCount
        this.views = info.views
        this.isAPoll = info.isAPoll
        this.pollChoices = pollChoices
        this.isPollingAllowed = info.isPollingAllowed
        this.location = info.location
        this.isACommentTweet = info.isACommentTweet
        this.isQuoteTweet = info.isQuoteTweet
        this.isRepostTweet = info.isRepostTweet
        this.isLikedTweet = info.isLikedTweet
        this.parentTweetId = info.parentTweetId
    }
}
