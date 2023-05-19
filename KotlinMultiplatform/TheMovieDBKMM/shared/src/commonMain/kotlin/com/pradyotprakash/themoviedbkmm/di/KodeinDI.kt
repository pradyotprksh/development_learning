package com.pradyotprakash.themoviedbkmm.di

import com.pradyotprakash.themoviedbkmm.core.network.NetworkClient
import com.pradyotprakash.themoviedbkmm.core.services.DiscoverService
import com.pradyotprakash.themoviedbkmm.data.services.DiscoverServiceImpl
import com.pradyotprakash.themoviedbkmm.presenter.DiscoverPresenter
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class KodeinDI {
    private val serviceModel = DI.Module("services") {
        bindSingleton { Api.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
        bindSingleton<DiscoverService> { DiscoverServiceImpl(instance()) }
    }
    private val presenterModel = DI.Module("presenters") {
        bindProvider { DiscoverPresenter(instance()) }
    }

    val di = DI {
        import(serviceModel)
        import(presenterModel)
    }
}

object DiFactory {
    val di = KodeinDI().di
}