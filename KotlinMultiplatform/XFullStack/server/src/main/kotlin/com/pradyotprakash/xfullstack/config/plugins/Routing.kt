package com.pradyotprakash.xfullstack.config.plugins

import com.pradyotprakash.xfullstack.config.ModulesConfig
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.tags.TagsDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.authentication.authentication
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.bookmark.bookmark
import com.pradyotprakash.xfullstack.features.bookmark.controllers.BookmarkController
import com.pradyotprakash.xfullstack.features.follow.controllers.FollowController
import com.pradyotprakash.xfullstack.features.follow.follow
import com.pradyotprakash.xfullstack.features.migration.migration
import com.pradyotprakash.xfullstack.features.secrets.controllers.SecretsController
import com.pradyotprakash.xfullstack.features.secrets.secrets
import com.pradyotprakash.xfullstack.features.tags.controllers.TagsController
import com.pradyotprakash.xfullstack.features.tags.tags
import com.pradyotprakash.xfullstack.features.tweet.controllers.TweetController
import com.pradyotprakash.xfullstack.features.tweet.tweet
import com.pradyotprakash.xfullstack.features.utils.controllers.UtilsController
import com.pradyotprakash.xfullstack.features.utils.utils
import com.pradyotprakash.xfullstack.features.verification.controllers.VerificationController
import com.pradyotprakash.xfullstack.features.verification.verification
import com.pradyotprakash.xfullstack.features.view.controllers.ViewController
import com.pradyotprakash.xfullstack.features.view.views
import com.pradyotprakash.xfullstack.features.websockets.websockets
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.instance

fun Application.configureRouting() {
    val userDataSource by ModulesConfig.di.instance<UserDataSource>()
    val tweetDataSource by ModulesConfig.di.instance<TweetDataSource>()
    val viewDataSource by ModulesConfig.di.instance<ViewDataSource>()
    val followDataSource by ModulesConfig.di.instance<FollowDataSource>()
    val bookmarkDataSource by ModulesConfig.di.instance<BookmarkDataSource>()
    val tagsDataSource by ModulesConfig.di.instance<TagsDataSource>()

    val tokenService by ModulesConfig.di.instance<TokenService>()
    val tokenConfig by ModulesConfig.di.instance<TokenConfig>()
    val hashingService by ModulesConfig.di.instance<HashingService>()

    val authenticationController by ModulesConfig.di.instance<AuthenticationController>()
    val utilsController by ModulesConfig.di.instance<UtilsController>()
    val verificationController by ModulesConfig.di.instance<VerificationController>()
    val tweetController by ModulesConfig.di.instance<TweetController>()
    val viewController by ModulesConfig.di.instance<ViewController>()
    val secretsController by ModulesConfig.di.instance<SecretsController>()
    val followController by ModulesConfig.di.instance<FollowController>()
    val bookmarkController by ModulesConfig.di.instance<BookmarkController>()
    val tagsController by ModulesConfig.di.instance<TagsController>()

    routing {
        authentication(
            authenticationController = authenticationController,
            hashingService = hashingService,
            tokenService = tokenService,
            userDataSource = userDataSource,
            tokenConfig = tokenConfig,
            followDataSource = followDataSource,
        )
        utils(
            userDataSource = userDataSource,
            utilsController = utilsController,
        )
        verification(
            verificationController = verificationController,
            userDataSource = userDataSource,
        )
        tweet(
            tweetController = tweetController,
            tweetDataSource = tweetDataSource,
            userDataSource = userDataSource,
            viewDataSource = viewDataSource,
            followDataSource = followDataSource,
            bookmarkDataSource = bookmarkDataSource,
            tagsDataSource = tagsDataSource,
        )
        websockets()
        views(
            viewController = viewController,
            viewDataSource = viewDataSource,
            userDataSource = userDataSource,
        )
        secrets(
            secretsController = secretsController,
            userDataSource = userDataSource,
        )
        migration(
            tweetDataSource = tweetDataSource,
        )
        follow(
            userDataSource = userDataSource,
            followDataSource = followDataSource,
            followController = followController,
        )
        bookmark(
            userDataSource = userDataSource,
            tweetDataSource = tweetDataSource,
            bookmarkDataSource = bookmarkDataSource,
            bookmarkController = bookmarkController,
        )
        tags(
            userDataSource = userDataSource,
            tweetDataSource = tweetDataSource,
            tagsController = tagsController,
            tagsDataSource = tagsDataSource,
        )
    }
}