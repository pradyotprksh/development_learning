package com.pradyotprakash.themoviedbkmm.di

import com.pradyotprakash.themoviedbkmm.core.network.NetworkClient
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class KodeinDI {
    private val serviceModel = DI.Module("services") {
        bindSingleton { Api.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
    }
    private val presenterModel = DI.Module("presenters") {}

    val di = DI {
        import(serviceModel)
        import(presenterModel)
    }
}

object DiFactory {
    val di = KodeinDI().di
}