package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.network.Api
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.service.BerryServiceImplementation
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import org.kodein.di.Kodein
import org.kodein.di.generic.*

object ModulesConfig {
    private val featuresModule = Kodein.Module("FEATURES") {
        bind() from provider { BerriesController(instance()) }
    }

    private val networkModule = Kodein.Module("NETWORK") {
        bind() from singleton { Api.createHttpClient() }
        bind() from singleton { NetworkClient(instance()) }
    }

    private val servicesModule = Kodein.Module("SERVICES") {
        bind<BerryService>() with provider { BerryServiceImplementation(instance()) }
    }

    internal val kodein = Kodein {
        import(featuresModule)
        import(networkModule)
        import(servicesModule)
    }
}