package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.core.database.XFullStackMongoDBClient
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SHA256HashingService
import com.pradyotprakash.xfullstack.core.security.token.JwtTokenService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.follow.MongoFollowDataSource
import com.pradyotprakash.xfullstack.data.tweet.MongoTweetDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.MongoUserDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.data.view.data.MongoViewDataSource
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate.AuthenticateController
import com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate.AuthenticateControllerImplementation
import com.pradyotprakash.xfullstack.features.authentication.controllers.login.LoginController
import com.pradyotprakash.xfullstack.features.authentication.controllers.login.LoginControllerImplementation
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterController
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterControllerImplementation
import com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo.UserInfoController
import com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo.UserInfoControllerImplementation
import com.pradyotprakash.xfullstack.features.bookmark.controllers.BookmarkController
import com.pradyotprakash.xfullstack.features.bookmark.controllers.bookmarkUpdate.BookmarkUpdateController
import com.pradyotprakash.xfullstack.features.bookmark.controllers.bookmarkUpdate.BookmarkUpdateControllerImplementation
import com.pradyotprakash.xfullstack.features.follow.controllers.FollowController
import com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate.FollowUpdateController
import com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate.FollowUpdateControllerImplementation
import com.pradyotprakash.xfullstack.features.secrets.controllers.SecretsController
import com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch.SecretsFetchController
import com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch.SecretsFetchControllerImplementation
import com.pradyotprakash.xfullstack.features.tweet.controllers.TweetController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate.TweetCreateUpdateController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate.TweetCreateUpdateControllerImplementation
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch.TweetFetchController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch.TweetFetchControllerImplementation
import com.pradyotprakash.xfullstack.features.utils.controllers.UtilsController
import com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails.ServerDetailsController
import com.pradyotprakash.xfullstack.features.utils.controllers.serverDetails.ServerDetailsControllerImplementation
import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidController
import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidControllerImplementation
import com.pradyotprakash.xfullstack.features.verification.controllers.VerificationController
import com.pradyotprakash.xfullstack.features.verification.controllers.userVerification.UserVerificationController
import com.pradyotprakash.xfullstack.features.verification.controllers.userVerification.UserVerificationControllerImplementation
import com.pradyotprakash.xfullstack.features.view.controllers.ViewController
import com.pradyotprakash.xfullstack.features.view.controllers.create.ViewCreateController
import com.pradyotprakash.xfullstack.features.view.controllers.create.ViewCreateControllerImplementation
import di.SharedModulesDi
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import utils.Constants
import utils.Constants.ConstValues.TOKEN_EXPIRES_IN
import utils.Constants.Keys.JWT_SECRET

object ModulesConfig {
    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { XFullStackMongoDBClient.getDatabase() }

        bindProvider<UserDataSource> { MongoUserDataSource(instance()) }
        bindProvider<TweetDataSource> { MongoTweetDataSource(instance()) }
        bindProvider<ViewDataSource> { MongoViewDataSource(instance()) }
        bindProvider<FollowDataSource> { MongoFollowDataSource(instance()) }
    }

    private val securityModule = DI.Module("SECURITY") {
        bindProvider<TokenService> { JwtTokenService() }
        bindProvider<HashingService> { SHA256HashingService() }
        bindSingleton {
            TokenConfig(
                issuer = Constants.Jwt.ISSUER,
                audience = Constants.Jwt.AUDIENCE,
                expiresIn = TOKEN_EXPIRES_IN,
                secret = System.getenv(JWT_SECRET),
            )
        }
    }

    private val controllersModule = DI.Module("CONTROLLERS") {
        bindProvider<RegisterController> { RegisterControllerImplementation() }
        bindProvider<LoginController> { LoginControllerImplementation() }
        bindProvider<AuthenticateController> { AuthenticateControllerImplementation() }
        bindProvider<UserInfoController> { UserInfoControllerImplementation() }

        bindProvider<UsernameValidController> { UsernameValidControllerImplementation() }
        bindProvider<ServerDetailsController> { ServerDetailsControllerImplementation() }

        bindProvider<UserVerificationController> { UserVerificationControllerImplementation() }

        bindProvider<TweetCreateUpdateController> {
            TweetCreateUpdateControllerImplementation(
                SharedModulesDi.Instance.geminiRepository,
            )
        }
        bindProvider<TweetFetchController> { TweetFetchControllerImplementation() }

        bindProvider<ViewCreateController> { ViewCreateControllerImplementation() }

        bindProvider<SecretsFetchController> { SecretsFetchControllerImplementation() }

        bindProvider<FollowUpdateController> { FollowUpdateControllerImplementation() }

        bindProvider<BookmarkUpdateController> { BookmarkUpdateControllerImplementation() }
    }

    private val featuresModule = DI.Module("FEATURES") {
        bindProvider { AuthenticationController(instance(), instance(), instance(), instance()) }

        bindProvider { UtilsController(instance(), instance()) }

        bindProvider { VerificationController(instance()) }

        bindProvider { TweetController(instance(), instance()) }

        bindProvider { ViewController(instance()) }

        bindProvider { SecretsController(instance()) }

        bindProvider { FollowController(instance()) }

        bindProvider { BookmarkController(instance()) }
    }

    val di = DI {
        importAll(
            databaseModule,
            securityModule,
            controllersModule,
            featuresModule,
        )
    }
}