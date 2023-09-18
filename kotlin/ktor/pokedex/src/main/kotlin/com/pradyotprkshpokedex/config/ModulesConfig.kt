package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.network.Api
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.core.service.ContestService
import com.pradyotprkshpokedex.core.service.EncounterService
import com.pradyotprkshpokedex.core.service.EvolutionService
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.core.service.MachineService
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.service.BerryServiceImplementation
import com.pradyotprkshpokedex.domain.service.ContestServiceImplementation
import com.pradyotprkshpokedex.domain.service.EncounterServiceImplementation
import com.pradyotprkshpokedex.domain.service.EvolutionServiceImplementation
import com.pradyotprkshpokedex.domain.service.GameServiceImplementation
import com.pradyotprkshpokedex.domain.service.ItemServiceImplementation
import com.pradyotprkshpokedex.domain.service.LocationServiceImplementation
import com.pradyotprkshpokedex.domain.service.MachineServiceImplementation
import com.pradyotprkshpokedex.domain.service.MoveServiceImplementation
import com.pradyotprkshpokedex.domain.service.PokemonServiceImplementation
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.controllers.BerryController
import com.pradyotprkshpokedex.features.berries.controllers.BerryFirmnessController
import com.pradyotprkshpokedex.features.berries.controllers.BerryFlavorController
import com.pradyotprkshpokedex.features.berries.controllers.implementation.BerryControllerImplementation
import com.pradyotprkshpokedex.features.berries.controllers.implementation.BerryFirmnessControllerImplementation
import com.pradyotprkshpokedex.features.berries.controllers.implementation.BerryFlavorControllerImplementation
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionChainController
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionTriggerController
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionsController
import com.pradyotprkshpokedex.features.evolution.controllers.implementation.EvolutionChainControllerImplementation
import com.pradyotprkshpokedex.features.evolution.controllers.implementation.EvolutionTriggerControllerImplementation
import com.pradyotprkshpokedex.features.machines.controllers.MachineController
import com.pradyotprkshpokedex.features.machines.controllers.MachinesController
import com.pradyotprkshpokedex.features.machines.controllers.implementation.MachinesControllerImplementation
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

        bind<MachineController>() with provider { MachinesControllerImplementation(instance()) }
        bind() from provider { MachinesController(instance()) }

        bind<EvolutionChainController>() with provider { EvolutionChainControllerImplementation(instance()) }
        bind<EvolutionTriggerController>() with provider { EvolutionTriggerControllerImplementation(instance()) }
        bind() from provider { EvolutionsController(instance(), instance()) }
    }

    private val networkModule = Kodein.Module("NETWORK") {
        bind() from singleton { Api.createHttpClient() }
        bind() from singleton { NetworkClient(instance()) }
    }

    private val servicesModule = Kodein.Module("SERVICES") {
        bind<BerryService>() with provider { BerryServiceImplementation(instance()) }
        bind<ContestService>() with provider { ContestServiceImplementation(instance()) }
        bind<EncounterService>() with provider { EncounterServiceImplementation(instance()) }
        bind<EvolutionService>() with provider { EvolutionServiceImplementation(instance()) }
        bind<GameService>() with provider { GameServiceImplementation(instance()) }
        bind<ItemService>() with provider { ItemServiceImplementation(instance()) }
        bind<LocationService>() with provider { LocationServiceImplementation(instance()) }
        bind<MachineService>() with provider { MachineServiceImplementation(instance()) }
        bind<MoveService>() with provider { MoveServiceImplementation(instance()) }
        bind<PokemonService>() with provider { PokemonServiceImplementation(instance()) }
    }

    internal val kodein = Kodein {
        import(featuresModule)
        import(networkModule)
        import(servicesModule)
    }
}