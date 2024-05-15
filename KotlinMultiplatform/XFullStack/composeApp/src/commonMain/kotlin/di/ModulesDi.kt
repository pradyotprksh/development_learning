package di

import core.network.Api
import org.kodein.di.DI
import org.kodein.di.bindSingleton

object ModulesDi {
    private val networkModule = DI.Module("NETWORK") {
        bindSingleton { Api.createHttpClient() }
    }

    val di = DI {
        importAll(
            networkModule,
        )
    }
}