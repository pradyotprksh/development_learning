package com.pradyotprakash.jwitter.di

import com.pradyotprakash.jwitter.core.network.NetworkClient
import com.pradyotprakash.jwitter.core.services.UserService
import com.pradyotprakash.jwitter.data.services.UserServiceImplementation
import com.pradyotprakash.jwitter.presenter.UserPresenter
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class KodeinDI {
    private val serviceModel = DI.Module("services") {
        bindSingleton { Api.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
        bindSingleton<UserService> { UserServiceImplementation(instance()) }
    }
    private val presenterModel = DI.Module("presenters") {
        bindProvider { UserPresenter(instance()) }
    }

    val di = DI {
        import(serviceModel)
        import(presenterModel)
    }
}

object DiFactory {
    private val di = KodeinDI().di

    val userPresenter: UserPresenter by di.instance()
}