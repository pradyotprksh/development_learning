package core.parser

import app.pages.createTweet.state.TweetDetails
import core.models.request.TweetRequest

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
