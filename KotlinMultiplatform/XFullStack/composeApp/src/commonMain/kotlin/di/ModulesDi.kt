package di

import core.database.XFullStackDatabaseClient
import core.network.NetworkClient
import core.network.XFullStackHttpClient
import data.device.tweet.TweetDBServiceImplementation
import data.device.user.current.CurrentUserDBServiceImplementation
import data.remote.server.utils.ServerUtilsRemoteServiceImplementation
import data.remote.tweet.TweetRemoteServiceImplementation
import data.remote.user.current.CurrentUserRemoteServiceImplementation
import data.remote.user.verification.UserVerificationRemoteServiceImplementation
import domain.repositories.server.utils.ServerUtilsRepository
import domain.repositories.server.utils.ServerUtilsRepositoryImplementation
import domain.repositories.tweet.TweetRepository
import domain.repositories.tweet.TweetRepositoryImplementation
import domain.repositories.user.current.CurrentUserRepository
import domain.repositories.user.current.CurrentUserRepositoryImplementation
import domain.repositories.user.verification.UserVerificationRepository
import domain.repositories.user.verification.UserVerificationRepositoryImplementation
import domain.services.server.utils.ServerUtilsRemoteService
import domain.services.tweet.TweetDBService
import domain.services.tweet.TweetRemoteService
import domain.services.user.current.CurrentUserDBService
import domain.services.user.current.CurrentUserRemoteService
import domain.services.user.verification.UserVerificationRemoteService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

object ModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton { XFullStackHttpClient.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
    }

    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { XFullStackDatabaseClient.createDatabaseClient() }
    }

    private val servicesDBModule = DI.Module("SERVICES_DB") {
        bindProvider<CurrentUserDBService> { CurrentUserDBServiceImplementation(instance()) }

        bindProvider<TweetDBService> { TweetDBServiceImplementation(instance()) }
    }

    private val servicesRemoteModule = DI.Module("SERVICES_REMOTE") {
        bindProvider<CurrentUserRemoteService> { CurrentUserRemoteServiceImplementation(instance()) }
        bindProvider<UserVerificationRemoteService> {
            UserVerificationRemoteServiceImplementation(
                instance()
            )
        }

        bindProvider<ServerUtilsRemoteService> { ServerUtilsRemoteServiceImplementation(instance()) }

        bindProvider<TweetRemoteService> { TweetRemoteServiceImplementation(instance()) }
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
}