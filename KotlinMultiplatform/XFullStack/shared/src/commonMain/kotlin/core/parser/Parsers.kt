package core.parser

import core.models.realm.ChatResponseDB
import core.models.realm.CurrentUserInfoDB
import core.models.realm.MessageRequestDB
import core.models.realm.MessageResponseDB
import core.models.realm.PollChoicesDB
import core.models.realm.RequestsDB
import core.models.realm.TagsDB
import core.models.realm.TweetDB
import core.models.realm.TweetRequestDB
import core.models.realm.UserInfoDB
import core.models.request.MessageRequest
import core.models.request.TweetRequest
import core.models.response.ChatResponse
import core.models.response.MessageResponse
import core.models.response.PollChoicesResponse
import core.models.response.TagsResponse
import core.models.response.TweetResponse
import core.models.response.UserInfoResponse
import io.realm.kotlin.ext.toRealmList
import utils.Constants.Keys.MESSAGE_REQUEST
import utils.Constants.Keys.TWEET_REQUEST

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
        this.followers = info.followers
        this.chatId = info.chatId
    }
}

fun UserInfoResponse.parseToUserInfoDB() = this.let { info ->
    UserInfoDB().apply {
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
        this.isFollowingCurrentUser = info.isFollowingCurrentUser
        this.isFollowedByCurrentUser = info.isFollowedByCurrentUser
        this.isSameUser = info.isSameUser
        this.chatId = info.chatId
    }
}

fun CurrentUserInfoDB.parseToCurrentUserResponse() = UserInfoResponse(
    id = this.userId,
    name = this.name,
    username = this.username,
    bio = this.bio,
    emailAddress = this.emailAddress,
    phoneNumber = this.phoneNumber,
    profilePicture = this.profilePicture,
    dateOfBirth = this.dateOfBirth,
    following = this.following,
    followers = this.followers,
    isFollowedByCurrentUser = false,
    isFollowingCurrentUser = false,
    isSameUser = true,
    chatId = this.chatId,
)

fun UserInfoDB.parseToCurrentUserResponse() = UserInfoResponse(
    id = this.userId,
    name = this.name,
    username = this.username,
    bio = this.bio,
    emailAddress = this.emailAddress,
    phoneNumber = this.phoneNumber,
    profilePicture = this.profilePicture,
    dateOfBirth = this.dateOfBirth,
    following = this.following,
    followers = this.followers,
    isFollowingCurrentUser = this.isFollowingCurrentUser,
    isFollowedByCurrentUser = this.isFollowedByCurrentUser,
    isSameUser = this.isSameUser,
    chatId = this.chatId,
)

fun PollChoicesResponse.parseToPollChoicesDB() = this.let { info ->
    PollChoicesDB().apply {
        this.id = info.id
        this.choice = info.choice
        this.voteCount = info.voteCount
    }
}

fun PollChoicesDB.parseToPollChoicesResponse() = PollChoicesResponse(
    id = this.id,
    choice = this.choice,
    voteCount = this.voteCount,
)

fun TweetResponse.parseToTweetsDB(): TweetDB = this.let { info ->
    TweetDB().apply {
        this.tweetId = info.id
        this.tweet = info.tweet
        this.createdBy = info.createdBy?.parseToUserInfoDB()
        this.tweetedOnTimestamp = info.tweetedOnTimestamp
        this.tweetedOn = info.tweetedOn
        this.media = info.media.toRealmList()
        this.gif = info.gif.toRealmList()
        this.commentCount = info.commentCount
        this.likeCount = info.likesCount
        this.repostsCount = info.repostsCount
        this.quotesCount = info.quotesCount
        this.bookmarkCount = info.bookmarksCount
        this.views = info.views
        this.isAPoll = info.isAPoll
        this.pollChoices = info.pollChoices.map { it.parseToPollChoicesDB() }.toRealmList()
        this.pollingEndTime = info.pollingEndTime
        this.isPollingAllowed = info.isPollingAllowed
        this.location = info.location
        this.isACommentTweet = info.isACommentTweet
        this.isQuoteTweet = info.isQuoteTweet
        this.isRepostTweet = info.isRepostTweet
        this.isLikedTweet = info.isLikedTweet
        this.parentTweetId = info.parentTweetId
        this.isLikedByCurrentUser = info.isLikedByCurrentUser
        this.isBookmarkedByCurrentUser = info.isBookmarkedByCurrentUser
        this.scheduledOnTweet = info.scheduledOnTweet
        this.parentTweetDetailsNotFound = info.parentTweetDetailsNotFound
        this.parentTweetDetails = info.parentTweetDetails?.parseToTweetsDB()
    }
}

fun TweetDB.parseToTweetResponse(): TweetResponse = TweetResponse(
    id = this.tweetId,
    tweet = this.tweet,
    createdBy = this.createdBy?.parseToCurrentUserResponse(),
    tweetedOnTimestamp = this.tweetedOnTimestamp,
    tweetedOn = this.tweetedOn,
    media = this.media,
    gif = this.gif,
    commentCount = this.commentCount,
    likesCount = this.likeCount,
    repostsCount = this.repostsCount,
    quotesCount = this.quotesCount,
    bookmarksCount = this.bookmarkCount,
    views = this.views,
    isAPoll = this.isAPoll,
    pollChoices = this.pollChoices.map { it.parseToPollChoicesResponse() },
    pollingEndTime = this.pollingEndTime,
    isPollingAllowed = this.isPollingAllowed,
    location = this.location,
    isACommentTweet = this.isACommentTweet,
    isQuoteTweet = this.isQuoteTweet,
    isRepostTweet = this.isRepostTweet,
    isLikedTweet = this.isLikedTweet,
    parentTweetId = this.parentTweetId,
    isLikedByCurrentUser = this.isLikedByCurrentUser,
    scheduledOnTweet = this.scheduledOnTweet,
    parentTweetDetailsNotFound = this.parentTweetDetailsNotFound,
    isBookmarkedByCurrentUser = this.isBookmarkedByCurrentUser,
    parentTweetDetails = this.parentTweetDetails?.parseToTweetResponse(),
)

fun MessageRequest.parseToRequestDb() = this.let { message ->
    RequestsDB().apply {
        this.requestType = MESSAGE_REQUEST
        this.message = MessageRequestDB().apply {
            this.chatId = message.chatId
            this.message = message.message
            this.messageTo = message.messageTo
            this.users = message.users.toRealmList()
            this.media = message.media.toRealmList()
            this.gif = message.gif.toRealmList()
            this.replyMessageId = message.replyMessageId
            this.forwardMessageId = message.forwardMessageId
            this.reaction = message.reaction
            this.audio = message.audio
            this.tweetId = message.tweetId
        }
    }
}

fun RequestsDB.parseToMessageRequest() = this.message?.let { message ->
    MessageRequest(
        chatId = message.chatId,
        message = message.message,
        messageTo = message.messageTo,
        users = message.users,
        media = message.media,
        gif = message.gif,
        replyMessageId = message.replyMessageId,
        forwardMessageId = message.forwardMessageId,
        reaction = message.reaction,
        audio = message.audio,
        tweetId = message.tweetId,
    )
}

fun List<TweetRequest>.parseToRequestDb() = this.let { tweets ->
    val tweetsParse = tweets.map { tweetDetails ->
        TweetRequestDB().apply {
            this.tweet = tweetDetails.tweet
            this.media = tweetDetails.media.toRealmList()
            this.gif = tweetDetails.gif.toRealmList()
            this.isAPoll = tweetDetails.isAPoll
            this.pollChoices = tweetDetails.pollChoices.toRealmList()
            this.pollHour = tweetDetails.pollHour
            this.pollMinute = tweetDetails.pollMinute
            this.pollSeconds = tweetDetails.pollSeconds
            this.location = tweetDetails.location
            this.isScheduledTweet = tweetDetails.isScheduledTweet
            this.scheduledOnTweet = tweetDetails.scheduledOnTweet
            this.isQuoteTweet = tweetDetails.isQuoteTweet
            this.isRepostTweet = tweetDetails.isRepostTweet
            this.isACommentTweet = tweetDetails.isACommentTweet
            this.isLikedTweet = tweetDetails.isLikedTweet
            this.parentTweetId = tweetDetails.parentTweetId
        }
    }

    RequestsDB().apply {
        this.requestType = TWEET_REQUEST
        this.tweets = tweetsParse.toRealmList()
    }
}

fun RequestsDB.parseToTweetRequest() = this.tweets.map { db ->
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

fun TagsResponse.parseToTagsDB() = this.let { tag ->
    TagsDB().apply {
        this.id = tag.tag
        this.count = tag.count
        this.totalTweets = tag.totalTweets
        this.isTrending = tag.isTrending
    }
}

fun TagsDB.parseToTagsResponse() = TagsResponse(
    tag = this.id, count = this.count, totalTweets = this.totalTweets, isTrending = this.isTrending
)

fun ChatResponse.parseToChatResponseDB() = this.let { chat ->
    ChatResponseDB().apply {
        this.chatId = chat.chatId
        this.users = chat.users.map { it.parseToUserInfoDB() }.toRealmList()
        this.createdOn = chat.createdOn
        this.createdBy = chat.createdBy
        this.isGroup = chat.isGroup
        this.isDirect = chat.isDirect
        this.lastMessageDetails = chat.lastMessageDetails?.parseToMessageResponseDB()
    }
}

fun ChatResponseDB.parseToChatResponse() = ChatResponse(
    chatId = this.chatId,
    users = this.users.map { it.parseToCurrentUserResponse() },
    createdOn = this.createdOn,
    createdBy = this.createdBy,
    isGroup = this.isGroup,
    isDirect = this.isDirect,
    lastMessageDetails = this.lastMessageDetails?.parseToMessageResponse(),
)

fun MessageResponse.parseToMessageResponseDB(): MessageResponseDB = this.let { message ->
    MessageResponseDB().apply {
        this.id = message.id
        this.chatId = message.chatId
        this.message = message.message
        this.messageOn = message.messageOn
        this.messageTime = message.messageTime
        this.messageTimeAgo = message.messageTimeAgo
        this.messageBy = message.messageBy?.parseToUserInfoDB()
        this.messageGroup = message.messageGroup
        this.isSendByCurrentUser = message.isSendByCurrentUser
        this.isRead = message.isRead
        this.media = message.media.toRealmList()
        this.gif = message.gif.toRealmList()
        this.replyMessageDetails = message.replyMessageDetails?.parseToMessageResponseDB()
        this.notificationMessage = message.notificationMessage
        this.forwardMessageDetails = message.forwardMessageDetails?.parseToMessageResponseDB()
        this.reaction = message.reaction.toRealmList()
        this.audio = message.audio
        this.tweetDetails = message.tweetDetails?.parseToTweetsDB()
    }
}

fun MessageResponseDB.parseToMessageResponse(): MessageResponse = MessageResponse(
    id = this.id,
    chatId = this.chatId,
    message = this.message,
    messageOn = this.messageOn,
    messageTime = this.messageTime,
    messageTimeAgo = this.messageTimeAgo,
    messageBy = this.messageBy?.parseToCurrentUserResponse(),
    isSendByCurrentUser = this.isSendByCurrentUser,
    messageGroup = this.messageGroup,
    isRead = this.isRead,
    media = this.media,
    gif = this.gif,
    replyMessageDetails = this.replyMessageDetails?.parseToMessageResponse(),
    notificationMessage = this.notificationMessage,
    forwardMessageDetails = this.forwardMessageDetails?.parseToMessageResponse(),
    reaction = this.reaction,
    audio = this.audio,
    tweetDetails = this.tweetDetails?.parseToTweetResponse(),
)