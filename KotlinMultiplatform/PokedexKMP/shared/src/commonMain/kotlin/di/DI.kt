package di

import core.navigator.Navigator
import core.network.Network
import di.client.NetworkClient
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class DI {
    private val network = DI.Module("network") {
        bindSingleton { Network.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
    }

    private val navigator = DI.Module("navigator") {
        bindSingleton { Navigator() }
    }

    val di = DI {
        import(network)
        import(navigator)
    }
}

object DiFactory {
    private val di = DI().di
    val navigator: Navigator by di.instance()
}
