package di

import core.database.XFullStackDatabaseClient
import core.network.GeminiHttpClient
import core.network.NetworkClient
import core.network.XFullStackHttpClient
import data.device.tweet.TweetDBServiceImplementation
import data.device.user.current.CurrentUserDBServiceImplementation
import data.device.view.ViewDBServiceImplementation
import data.remote.gemini.GeminiRemoteServiceImplementation
import data.remote.server.utils.ServerUtilsRemoteServiceImplementation
import data.remote.tweet.TweetRemoteServiceImplementation
import data.remote.user.current.CurrentUserRemoteServiceImplementation
import data.remote.user.verification.UserVerificationRemoteServiceImplementation
import data.remote.view.ViewRemoteServiceImplementation
import data.remote.websocket.WebsocketRemoteServiceImplementation
import domain.repositories.gemini.GeminiRepository
import domain.repositories.gemini.GeminiRepositoryImplementation
import domain.repositories.server.utils.ServerUtilsRepository
import domain.repositories.server.utils.ServerUtilsRepositoryImplementation
import domain.repositories.tweet.TweetRepository
import domain.repositories.tweet.TweetRepositoryImplementation
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.user.current.CurrentUserRepositoryImplementation
import domain.repositories.user.verification.UserVerificationRepository
import domain.repositories.user.verification.UserVerificationRepositoryImplementation
import domain.repositories.view.ViewRepository
import domain.repositories.view.ViewRepositoryImplementation
import domain.repositories.websocket.WebsocketRepository
import domain.repositories.websocket.WebsocketRepositoryImplementation
import domain.services.gemini.GeminiRemoteService
import domain.services.server.utils.ServerUtilsRemoteService
import domain.services.tweet.TweetDBService
import domain.services.tweet.TweetRemoteService
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
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
    }
}