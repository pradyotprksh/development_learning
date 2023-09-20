package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.controller.DefaultController
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
import com.pradyotprkshpokedex.domain.service.DefaultServiceImplementation
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
import com.pradyotprkshpokedex.features.contests.controllers.ContestEffectController
import com.pradyotprkshpokedex.features.contests.controllers.ContestTypeController
import com.pradyotprkshpokedex.features.contests.controllers.ContestsController
import com.pradyotprkshpokedex.features.contests.controllers.SuperContestEffectController
import com.pradyotprkshpokedex.features.contests.controllers.implementation.ContestEffectControllerImplementation
import com.pradyotprkshpokedex.features.contests.controllers.implementation.ContestTypeControllerImplementation
import com.pradyotprkshpokedex.features.contests.controllers.implementation.SuperContestEffectControllerImplementation
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
    private val controllerModule = Kodein.Module("CONTROLLER") {
        bind<BerryController>() with provider { BerryControllerImplementation(instance(), instance()) }
        bind<BerryFirmnessController>() with provider { BerryFirmnessControllerImplementation(instance(), instance()) }
        bind<BerryFlavorController>() with provider { BerryFlavorControllerImplementation(instance(), instance()) }

        bind<ContestEffectController>() with provider { ContestEffectControllerImplementation(instance(), instance()) }
        bind<ContestTypeController>() with provider { ContestTypeControllerImplementation(instance(), instance()) }
        bind<SuperContestEffectController>() with provider { SuperContestEffectControllerImplementation(instance(), instance()) }

        bind<MachineController>() with provider { MachinesControllerImplementation(instance(), instance()) }

        bind<EvolutionChainController>() with provider {
            EvolutionChainControllerImplementation(
                instance(),
                instance()
            )
        }
        bind<EvolutionTriggerController>() with provider {
            EvolutionTriggerControllerImplementation(
                instance(),
                instance()
            )
        }

        bind() from singleton { DefaultController(instance()) }
    }

    private val featuresModule = Kodein.Module("FEATURES") {
        bind() from provider { BerriesController(instance(), instance(), instance()) }
        bind() from provider { ContestsController(instance(), instance(), instance()) }
        bind() from provider { MachinesController(instance()) }
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
        bind() from provider { DefaultServiceImplementation(instance()) }
    }

    internal val kodein = Kodein {
        import(controllerModule)
        import(featuresModule)
        import(networkModule)
        import(servicesModule)
    }
}