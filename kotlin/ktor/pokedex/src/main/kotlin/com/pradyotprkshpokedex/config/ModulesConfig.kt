package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.network.Api
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.service.BerryServiceImplementation
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.controllers.BerryController
import com.pradyotprkshpokedex.features.berries.controllers.BerryFirmnessController
import com.pradyotprkshpokedex.features.berries.controllers.BerryFlavorController
import com.pradyotprkshpokedex.features.berries.controllers.implementation.BerryControllerImplementation
import com.pradyotprkshpokedex.features.berries.controllers.implementation.BerryFirmnessControllerImplementation
import com.pradyotprkshpokedex.features.berries.controllers.implementation.BerryFlavorControllerImplementation
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object ModulesConfig {
    private val featuresModule = Kodein.Module("FEATURES") {
        bind<BerryController>() with provider { BerryControllerImplementation(instance()) }
        bind<BerryFirmnessController>() with provider { BerryFirmnessControllerImplementation(instance()) }
        bind<BerryFlavorController>() with provider { BerryFlavorControllerImplementation(instance()) }
        bind() from provider { BerriesController(instance(), instance(), instance()) }
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