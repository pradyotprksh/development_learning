package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.network.Api
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object ModulesConfig {
    private val networkModule = Kodein.Module("NETWORK") {
        bind() from singleton { Api.createHttpClient() }
        bind() from singleton { NetworkClient(instance()) }
    }

    private val berriesModule = Kodein.Module("BERRIES") {
        bind() from singleton { BerriesController() }
    }

    internal val kodein = Kodein {
        import(networkModule)
        import(berriesModule)
    }
}