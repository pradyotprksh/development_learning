package com.pradyotprakash.xfullstack.features.websockets

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Routing
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import utils.Constants.Paths.Websockets.WEBSOCKETS
import utils.Constants.SuccessCode.TWEETS_UPDATE_SUCCESS_CODE

fun Routing.websockets(
    tweetDataSource: TweetDataSource,
) {
    authenticate {
        webSocket(
            WEBSOCKETS,
        ) {
            tweetDataSource.watchTweets().collect {
                outgoing.trySend(Frame.Text(TWEETS_UPDATE_SUCCESS_CODE))
            }
        }
    }
}