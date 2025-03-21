package di

import core.database.XFullStackDatabaseClient
import core.network.GeminiHttpClient
import core.network.NetworkClient
import core.network.XFullStackHttpClient
import data.device.chat.ChatDBServiceImplementation
import data.device.grok.GrokDBServiceImplementation
import data.device.request.RequestDBServiceImplementation
import data.device.tags.TagsDBServiceImplementation
import data.device.tweet.TweetDBServiceImplementation
import data.device.user.current.CurrentUserDBServiceImplementation
import data.device.user.user.UserDBServiceImplementation
import data.device.view.ViewDBServiceImplementation
import data.remote.chat.ChatRemoteServiceImplementation
import data.remote.file.FileRemoteServiceImplementation
import data.remote.gemini.GeminiRemoteServiceImplementation
import data.remote.grok.GrokRemoteServiceImplementation
import data.remote.server.utils.ServerUtilsRemoteServiceImplementation
import data.remote.tags.TagsRemoteServiceImplementation
import data.remote.tweet.TweetRemoteServiceImplementation
import data.remote.user.bookmark.UserBookmarkRemoteServiceImplementation
import data.remote.user.current.CurrentUserRemoteServiceImplementation
import data.remote.user.follow.UserFollowRemoteServiceImplementation
import data.remote.user.user.UserRemoteServiceImplementation
import data.remote.user.verification.UserVerificationRemoteServiceImplementation
import data.remote.view.ViewRemoteServiceImplementation
import data.remote.websocket.WebsocketRemoteServiceImplementation
import domain.repositories.chat.ChatRepository
import domain.repositories.chat.ChatRepositoryImplementation
import domain.repositories.file.FileRepository
import domain.repositories.file.FileRepositoryImplementation
import domain.repositories.gemini.GeminiRepository
import domain.repositories.gemini.GeminiRepositoryImplementation
import domain.repositories.grok.GrokRepository
import domain.repositories.grok.GrokRepositoryImplementation
import domain.repositories.request.RequestRepository
import domain.repositories.request.RequestRepositoryImplementation
import domain.repositories.server.utils.ServerUtilsRepository
import domain.repositories.server.utils.ServerUtilsRepositoryImplementation
import domain.repositories.slides.SlidesRepository
import domain.repositories.slides.SlidesRepositoryImplementation
import domain.repositories.tags.TagsRepository
import domain.repositories.tags.TagsRepositoryImplementation
import domain.repositories.tweet.TweetRepository
import domain.repositories.tweet.TweetRepositoryImplementation
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.user.current.CurrentUserRepositoryImplementation
import domain.repositories.user.user.UserRepository
import domain.repositories.user.user.UserRepositoryImplementation
import domain.repositories.user.verification.UserVerificationRepository
import domain.repositories.user.verification.UserVerificationRepositoryImplementation
import domain.repositories.view.ViewRepository
import domain.repositories.view.ViewRepositoryImplementation
import domain.repositories.websocket.WebsocketRepository
import domain.repositories.websocket.WebsocketRepositoryImplementation
import domain.services.chat.ChatDBService
import domain.services.chat.ChatRemoteService
import domain.services.file.FileRemoteService
import domain.services.gemini.GeminiRemoteService
import domain.services.grok.GrokDBService
import domain.services.grok.GrokRemoteService
import domain.services.request.RequestDBService
import domain.services.server.utils.ServerUtilsRemoteService
import domain.services.tags.TagsDBService
import domain.services.tags.TagsRemoteService
import domain.services.tweet.TweetDBService
import domain.services.tweet.TweetRemoteService
import domain.services.user.bookmark.UserBookmarkRemoteService
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
import domain.services.user.follow.UserFollowRemoteService
import domain.services.user.user.UserDBService
import domain.services.user.user.UserRemoteService
import domain.services.user.verification.UserVerificationRemoteService
import domain.services.view.ViewDBService
import domain.services.view.ViewRemoteService
import domain.services.websocket.WebsocketRemoteService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton
import utils.Constants.ConstValues.GEMINI_HTTP_CLIENT
import utils.Constants.ConstValues.GEMINI_NETWORK_CLIENT
import utils.Constants.ConstValues.XFULLSTACK_HTTP_CLIENT
import utils.Constants.ConstValues.XFULLSTACK_NETWORK_CLIENT

object SharedModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton(tag = XFULLSTACK_HTTP_CLIENT) { XFullStackHttpClient.createHttpClient() }
        bindSingleton(tag = GEMINI_HTTP_CLIENT) { GeminiHttpClient.createHttpClient() }
        bindSingleton(tag = XFULLSTACK_NETWORK_CLIENT) { NetworkClient(instance(tag = XFULLSTACK_HTTP_CLIENT)) }
        bindSingleton(tag = GEMINI_NETWORK_CLIENT) { NetworkClient(instance(tag = GEMINI_HTTP_CLIENT)) }
    }

    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { XFullStackDatabaseClient.createDatabaseClient() }
    }

    private val servicesDBModule = DI.Module("SERVICES_DB") {
        bindProvider<CurrentUserDBService> { CurrentUserDBServiceImplementation(instance()) }

        bindProvider<TweetDBService> { TweetDBServiceImplementation(instance()) }

        bindProvider<ViewDBService> { ViewDBServiceImplementation(instance()) }

        bindProvider<UserDBService> { UserDBServiceImplementation(instance()) }

        bindProvider<RequestDBService> { RequestDBServiceImplementation(instance()) }

        bindProvider<TagsDBService> { TagsDBServiceImplementation(instance()) }

        bindProvider<ChatDBService> { ChatDBServiceImplementation(instance()) }

        bindProvider<GrokDBService> { GrokDBServiceImplementation(instance()) }
    }

    private val servicesRemoteModule = DI.Module("SERVICES_REMOTE") {
        bindProvider<CurrentUserRemoteService> { CurrentUserRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }
        bindProvider<UserVerificationRemoteService> {
            UserVerificationRemoteServiceImplementation(
                instance(tag = XFULLSTACK_NETWORK_CLIENT)
            )
        }

        bindProvider<ServerUtilsRemoteService> { ServerUtilsRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<TweetRemoteService> { TweetRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<WebsocketRemoteService> { WebsocketRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<ViewRemoteService> { ViewRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<GeminiRemoteService> {
            GeminiRemoteServiceImplementation(
                instance(tag = GEMINI_NETWORK_CLIENT)
            )
        }

        bindProvider<UserFollowRemoteService> {
            UserFollowRemoteServiceImplementation(
                instance(tag = XFULLSTACK_NETWORK_CLIENT)
            )
        }

        bindProvider<UserBookmarkRemoteService> {
            UserBookmarkRemoteServiceImplementation(
                instance(tag = XFULLSTACK_NETWORK_CLIENT)
            )
        }

        bindProvider<UserRemoteService> {
            UserRemoteServiceImplementation(
                instance(tag = XFULLSTACK_NETWORK_CLIENT)
            )
        }

        bindProvider<TagsRemoteService> { TagsRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<ChatRemoteService> { ChatRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<FileRemoteService> { FileRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }

        bindProvider<GrokRemoteService> { GrokRemoteServiceImplementation(instance(tag = XFULLSTACK_NETWORK_CLIENT)) }
    }

    private val repositoriesModule = DI.Module("REPOSITORIES") {
        bind<CurrentUserRepository>() with singleton {
            CurrentUserRepositoryImplementation(
                instance(), instance()
            )
        }
        bind<UserVerificationRepository>() with singleton {
            UserVerificationRepositoryImplementation(instance())
        }

        bind<ServerUtilsRepository>() with singleton {
            ServerUtilsRepositoryImplementation(instance())
        }

        bind<TweetRepository>() with singleton {
            TweetRepositoryImplementation(instance(), instance())
        }

        bind<WebsocketRepository>() with singleton {
            WebsocketRepositoryImplementation(instance())
        }

        bind<ViewRepository>() with singleton {
            ViewRepositoryImplementation(instance(), instance())
        }

        bind<GeminiRepository>() with singleton {
            GeminiRepositoryImplementation(instance())
        }

        bind<UserRepository>() with singleton {
            UserRepositoryImplementation(instance(), instance())
        }

        bind<RequestRepository>() with singleton {
            RequestRepositoryImplementation(
                instance(),
                instance(),
                instance(),
                instance(),
                instance(),
            )
        }

        bind<TagsRepository>() with singleton {
            TagsRepositoryImplementation(instance(), instance())
        }

        bind<ChatRepository>() with singleton {
            ChatRepositoryImplementation(instance(), instance())
        }

        bind<FileRepository>() with singleton {
            FileRepositoryImplementation(instance())
        }

        bind<GrokRepository>() with singleton {
            GrokRepositoryImplementation(instance(), instance())
        }

        bind<SlidesRepository>() with singleton {
            SlidesRepositoryImplementation()
        }
    }

    val di = DI {
        importAll(
            databaseModule,
            networkModule,
            servicesDBModule,
            servicesRemoteModule,
            repositoriesModule,
        )
    }

    object Instance {
        val currentUserRepository: CurrentUserRepository by di.instance()
        val userVerificationRepository: UserVerificationRepository by di.instance()
        val serverUtilsRepository: ServerUtilsRepository by di.instance()
        val tweetRepository: TweetRepository by di.instance()
        val websocketRepository: WebsocketRepository by di.instance()
        val viewRepository: ViewRepository by di.instance()
        val geminiRepository: GeminiRepository by di.instance()
        val userRepository: UserRepository by di.instance()
        val requestRepository: RequestRepository by di.instance()
        val tagsRepository: TagsRepository by di.instance()
        val chatRepository: ChatRepository by di.instance()
        val fileRepository: FileRepository by di.instance()
        val grokRepository: GrokRepository by di.instance()
        val slidesRepository: SlidesRepository by di.instance()
    }
}