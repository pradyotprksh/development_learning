package com.pradyotprakash.xfullstack.features.websockets

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import core.exception.UserDetailsNotFound
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.routing.Routing
import io.ktor.server.websocket.webSocket
import utils.Constants.Keys.USER_ID
import utils.Constants.Paths.Websockets.WEBSOCKETS
import utils.Constants.SuccessCode.TWEETS_UPDATE_SUCCESS_CODE
import utils.Logger
import utils.LoggerLevel

fun Routing.websockets(
    tweetDataSource: TweetDataSource,
) {
    authenticate {
        webSocket(
            WEBSOCKETS,
        ) {
            val principal = call.principal<JWTPrincipal>()
            val connectionBy =
                principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()
            Connections.addConnections(userId = connectionBy, session = this)

            try {
                tweetDataSource.watchTweets().collect {
                    Connections.sendMessageToAll(TWEETS_UPDATE_SUCCESS_CODE)
                }
            } catch (e: Exception) {
                Logger.log(LoggerLevel.Error, e.localizedMessage ?: e.toString())
                Connections.removeSession(connectionBy)
            }
        }
    }
}