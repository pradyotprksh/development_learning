package core.parser

import app.pages.createTweet.state.TweetDetails
import core.models.realm.CurrentUserInfoDB
import core.models.realm.PollChoicesDB
import core.models.realm.TweetDB
import core.models.realm.TweetRequestDB
import core.models.realm.TweetRequestsDB
import data.request.TweetRequest
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
        this.tweetedOnTimestamp = info.tweetedOnTimestamp
        this.tweetedOn = info.tweetedOn
        this.media = media
        this.gif = gif
        this.commentCount = info.commentCount
        this.retweetCount = info.retweetCount
        this.likeCount = info.likesCount
        this.views = info.views
        this.isAPoll = info.isAPoll
        this.pollChoices = pollChoices
        this.pollingEndTime = info.pollingEndTime
        this.isPollingAllowed = info.isPollingAllowed
        this.location = info.location
        this.isACommentTweet = info.isACommentTweet
        this.isQuoteTweet = info.isQuoteTweet
        this.isRepostTweet = info.isRepostTweet
        this.isLikedTweet = info.isLikedTweet
        this.parentTweetId = info.parentTweetId
        this.isLikedByCurrentUser = info.isLikedByCurrentUser
    }
}

fun TweetDetails.parseToTweetRequest() = TweetRequest(
    tweet = this.tweet,
    media = this.media,
    gif = this.gifs,
    isAPoll = this.isAPoll,
    pollChoices = this.pollChoices,
    pollHour = this.pollHour,
    pollMinute = this.pollMinute,
    pollSeconds = this.pollSeconds,
    location = this.location,
    isScheduledTweet = this.isScheduledTweet,
    scheduledOnTweet = this.scheduledOnTweet,
    isQuoteTweet = this.isQuoteTweet,
)

fun List<TweetRequest>.parseToTweetRequestDb() = this.let { tweets ->
    val tweetsParse = tweets.map { tweetDetails ->
        val media = realmListOf<String>()
        tweetDetails.media.forEach { media.add(it) }

        val gif = realmListOf<String>()
        tweetDetails.gif.forEach { gif.add(it) }

        val pollChoices = realmListOf<String>()
        tweetDetails.pollChoices.forEach { pollChoices.add(it) }

        TweetRequestDB().apply {
            this.tweet = tweetDetails.tweet
            this.media = media
            this.gif = gif
            this.isAPoll = tweetDetails.isAPoll
            this.pollChoices = pollChoices
            this.pollHour = tweetDetails.pollHour
            this.pollMinute = tweetDetails.pollMinute
            this.pollSeconds = tweetDetails.pollSeconds
            this.location = tweetDetails.location
            this.isScheduledTweet = tweetDetails.isScheduledTweet
            this.scheduledOnTweet = tweetDetails.scheduledOnTweet
            this.isQuoteTweet = tweetDetails.isQuoteTweet
            this.isRepostTweet = tweetDetails.isRepostTweet
            this.isLikedTweet = tweetDetails.isLikedTweet
            this.parentTweetId = tweetDetails.parentTweetId
        }
    }

    val tweetDetailsDb = realmListOf<TweetRequestDB>()
    tweetDetailsDb.addAll(tweetsParse)

    TweetRequestsDB().apply {
        this.tweets = tweetDetailsDb
    }
}

fun TweetRequestsDB.parseToTweetRequest() = this.tweets.map { db ->
    TweetRequest(
        tweet = db.tweet,
        media = db.media,
        gif = db.gif,
        isAPoll = db.isAPoll,
        pollChoices = db.pollChoices,
        pollHour = db.pollHour,
        pollMinute = db.pollMinute,
        pollSeconds = db.pollSeconds,
        location = db.location,
        isScheduledTweet = db.isScheduledTweet,
        scheduledOnTweet = db.scheduledOnTweet,
        isQuoteTweet = db.isQuoteTweet,
        isRepostTweet = db.isRepostTweet,
        isLikedTweet = db.isLikedTweet,
        isACommentTweet = db.isACommentTweet,
        parentTweetId = db.parentTweetId
    )
}
