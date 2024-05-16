package di

import core.database.XFullStackDatabaseClient
import core.network.XFullStackHttpClient
import data.device.UserDBServiceImplementation
import domain.repositories.CurrentUserRepository
import domain.services.UserDBService
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object ModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton { XFullStackHttpClient(instance()) }
        bindSingleton { instance<XFullStackHttpClient>().createHttpClient() }
    }

    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { XFullStackDatabaseClient.createDatabaseClient() }
    }

    private val servicesModule = DI.Module("SERVICES") {
        bindProvider<UserDBService> { UserDBServiceImplementation(instance()) }
    }

    private val repositoriesModule = DI.Module("REPOSITORIES") {
        bindSingleton { CurrentUserRepository(instance()) }
    }

    val di = DI {
        importAll(
            databaseModule,
            networkModule,
            servicesModule,
            repositoriesModule,
        )
    }
}