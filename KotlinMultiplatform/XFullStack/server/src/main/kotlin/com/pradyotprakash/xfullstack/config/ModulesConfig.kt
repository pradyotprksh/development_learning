package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.core.database.XFullStackMongoDBClient
import com.pradyotprakash.xfullstack.core.helpers.GeminiHelper
import com.pradyotprakash.xfullstack.core.helpers.TagsHelper
import com.pradyotprakash.xfullstack.core.helpers.UserHelper
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SHA256HashingService
import com.pradyotprakash.xfullstack.core.security.token.JwtTokenService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.core.storage.XFullStackSupabaseClient
import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.bookmark.MongoBookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.chat.MongoChatDataSource
import com.pradyotprakash.xfullstack.data.file.FileDataSource
import com.pradyotprakash.xfullstack.data.file.SupabaseFileDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.follow.MongoFollowDataSource
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.message.MongoMessageDataSource
import com.pradyotprakash.xfullstack.data.tags.MongoTagsDataSource
import com.pradyotprakash.xfullstack.data.tags.TagsDataSource
import com.pradyotprakash.xfullstack.data.tweet.MongoTweetDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.MongoUserDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.MongoViewDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
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
import com.pradyotprakash.xfullstack.features.chat.controllers.ChatController
import com.pradyotprakash.xfullstack.features.chat.controllers.fetchMessages.FetchMessagesController
import com.pradyotprakash.xfullstack.features.chat.controllers.fetchMessages.FetchMessagesControllerImplementation
import com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage.SendMessageController
import com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage.SendMessageControllerImplementation
import com.pradyotprakash.xfullstack.features.file.controllers.FileController
import com.pradyotprakash.xfullstack.features.file.controllers.upload.FileUploadController
import com.pradyotprakash.xfullstack.features.file.controllers.upload.FileUploadControllerImplementation
import com.pradyotprakash.xfullstack.features.follow.controllers.FollowController
import com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate.FollowUpdateController
import com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate.FollowUpdateControllerImplementation
import com.pradyotprakash.xfullstack.features.grok.controllers.GrokController
import com.pradyotprakash.xfullstack.features.grok.controllers.grokChatController.GrokChatController
import com.pradyotprakash.xfullstack.features.grok.controllers.grokChatController.GrokChatControllerImplementation
import com.pradyotprakash.xfullstack.features.secrets.controllers.SecretsController
import com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch.SecretsFetchController
import com.pradyotprakash.xfullstack.features.secrets.controllers.secretsFetch.SecretsFetchControllerImplementation
import com.pradyotprakash.xfullstack.features.tags.controllers.TagsController
import com.pradyotprakash.xfullstack.features.tags.controllers.tagsFetch.TagsFetchController
import com.pradyotprakash.xfullstack.features.tags.controllers.tagsFetch.TagsFetchControllerImplementation
import com.pradyotprakash.xfullstack.features.tweet.controllers.TweetController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate.TweetCreateUpdateController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate.TweetCreateUpdateControllerImplementation
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch.TweetFetchController
import com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch.TweetFetchControllerImplementation
import com.pradyotprakash.xfullstack.features.users.controllers.UsersController
import com.pradyotprakash.xfullstack.features.users.controllers.canFollow.UsersCanFollowController
import com.pradyotprakash.xfullstack.features.users.controllers.canFollow.UsersCanFollowControllerImplementation
import com.pradyotprakash.xfullstack.features.users.controllers.info.UsersInfoController
import com.pradyotprakash.xfullstack.features.users.controllers.info.UsersInfoControllerImplementation
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
        bindProvider<BookmarkDataSource> { MongoBookmarkDataSource(instance()) }
        bindProvider<TagsDataSource> { MongoTagsDataSource(instance()) }
        bindProvider<ChatDataSource> { MongoChatDataSource(instance()) }
        bindProvider<MessageDataSource> { MongoMessageDataSource(instance()) }
    }

    private val pluginsModule = DI.Module("PLUGINS") {
        bindSingleton { XFullStackSupabaseClient.getStorage() }

        bindProvider<FileDataSource> { SupabaseFileDataSource(instance()) }
    }

    private val helpersModule = DI.Module("HELPERS") {
        bindProvider { UserHelper(instance(), instance(), instance()) }

        bindProvider { TagsHelper(instance()) }

        bindProvider { GeminiHelper() }
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
                instance(),
                instance(),
                instance(),
            )
        }
        bindProvider<TweetFetchController> { TweetFetchControllerImplementation() }

        bindProvider<ViewCreateController> { ViewCreateControllerImplementation() }

        bindProvider<SecretsFetchController> { SecretsFetchControllerImplementation() }

        bindProvider<FollowUpdateController> { FollowUpdateControllerImplementation() }

        bindProvider<BookmarkUpdateController> { BookmarkUpdateControllerImplementation() }

        bindProvider<TagsFetchController> { TagsFetchControllerImplementation() }

        bindProvider<SendMessageController> { SendMessageControllerImplementation() }
        bindProvider<FetchMessagesController> { FetchMessagesControllerImplementation() }

        bindProvider<FileUploadController> { FileUploadControllerImplementation() }

        bindProvider<GrokChatController> {
            GrokChatControllerImplementation(
                SharedModulesDi.Instance.geminiRepository,
                instance(),
            )
        }

        bindProvider<UsersInfoController> { UsersInfoControllerImplementation() }
        bindProvider<UsersCanFollowController> { UsersCanFollowControllerImplementation() }
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

        bindProvider { TagsController(instance()) }

        bindProvider { ChatController(instance(), instance()) }

        bindProvider { FileController(instance()) }

        bindProvider { GrokController(instance()) }

        bindProvider { UsersController(instance(), instance()) }
    }

    val di = DI {
        importAll(
            databaseModule,
            pluginsModule,
            helpersModule,
            securityModule,
            controllersModule,
            featuresModule,
        )
    }
}