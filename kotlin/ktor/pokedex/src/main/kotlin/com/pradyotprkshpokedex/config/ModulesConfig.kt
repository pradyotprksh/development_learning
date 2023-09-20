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
import com.pradyotprkshpokedex.features.encounters.controllers.EncounterConditionValuesController
import com.pradyotprkshpokedex.features.encounters.controllers.EncounterConditionsController
import com.pradyotprkshpokedex.features.encounters.controllers.EncounterMethodsController
import com.pradyotprkshpokedex.features.encounters.controllers.EncountersController
import com.pradyotprkshpokedex.features.encounters.controllers.implementation.EncounterConditionValuesControllerImplementation
import com.pradyotprkshpokedex.features.encounters.controllers.implementation.EncounterConditionsControllerImplementation
import com.pradyotprkshpokedex.features.encounters.controllers.implementation.EncounterMethodsControllerImplementation
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionChainController
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionTriggerController
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionsController
import com.pradyotprkshpokedex.features.evolution.controllers.implementation.EvolutionChainControllerImplementation
import com.pradyotprkshpokedex.features.evolution.controllers.implementation.EvolutionTriggerControllerImplementation
import com.pradyotprkshpokedex.features.games.controllers.GameGenerationsController
import com.pradyotprkshpokedex.features.games.controllers.GamePokedexesController
import com.pradyotprkshpokedex.features.games.controllers.GameVersionController
import com.pradyotprkshpokedex.features.games.controllers.GameVersionGroupController
import com.pradyotprkshpokedex.features.games.controllers.GamesController
import com.pradyotprkshpokedex.features.games.controllers.implementation.GameGenerationsControllerImplementation
import com.pradyotprkshpokedex.features.games.controllers.implementation.GamePokedexesControllerImplementation
import com.pradyotprkshpokedex.features.games.controllers.implementation.GameVersionControllerImplementation
import com.pradyotprkshpokedex.features.games.controllers.implementation.GameVersionGroupControllerImplementation
import com.pradyotprkshpokedex.features.items.controllers.ItemAttributesController
import com.pradyotprkshpokedex.features.items.controllers.ItemCategoriesController
import com.pradyotprkshpokedex.features.items.controllers.ItemController
import com.pradyotprkshpokedex.features.items.controllers.ItemFilingEffectsController
import com.pradyotprkshpokedex.features.items.controllers.ItemPocketsController
import com.pradyotprkshpokedex.features.items.controllers.ItemsController
import com.pradyotprkshpokedex.features.items.controllers.implementation.ItemAttributesControllerImplementation
import com.pradyotprkshpokedex.features.items.controllers.implementation.ItemCategoriesControllerImplementation
import com.pradyotprkshpokedex.features.items.controllers.implementation.ItemControllerImplementation
import com.pradyotprkshpokedex.features.items.controllers.implementation.ItemFilingEffectsControllerImplementation
import com.pradyotprkshpokedex.features.items.controllers.implementation.ItemPocketsControllerImplementation
import com.pradyotprkshpokedex.features.locations.controllers.LocationAreasController
import com.pradyotprkshpokedex.features.locations.controllers.LocationController
import com.pradyotprkshpokedex.features.locations.controllers.LocationsController
import com.pradyotprkshpokedex.features.locations.controllers.PalPakAreasController
import com.pradyotprkshpokedex.features.locations.controllers.RegionsController
import com.pradyotprkshpokedex.features.locations.controllers.implementation.LocationAreasControllerImplementation
import com.pradyotprkshpokedex.features.locations.controllers.implementation.LocationControllerImplementation
import com.pradyotprkshpokedex.features.locations.controllers.implementation.PalPakAreasControllerImplementation
import com.pradyotprkshpokedex.features.locations.controllers.implementation.RegionsControllerImplementation
import com.pradyotprkshpokedex.features.machines.controllers.MachineController
import com.pradyotprkshpokedex.features.machines.controllers.MachinesController
import com.pradyotprkshpokedex.features.machines.controllers.implementation.MachinesControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.MoveAilmentsController
import com.pradyotprkshpokedex.features.moves.controllers.MoveBattleStylesController
import com.pradyotprkshpokedex.features.moves.controllers.MoveCategoriesController
import com.pradyotprkshpokedex.features.moves.controllers.MoveController
import com.pradyotprkshpokedex.features.moves.controllers.MoveDamageClassesController
import com.pradyotprkshpokedex.features.moves.controllers.MoveLearnMethodsController
import com.pradyotprkshpokedex.features.moves.controllers.MoveTargetsController
import com.pradyotprkshpokedex.features.moves.controllers.MovesController
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveAilmentsControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveBattleStylesControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveCategoriesControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveDamageClassesControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveLearnMethodsControllerImplementation
import com.pradyotprkshpokedex.features.moves.controllers.implementation.MoveTargetsControllerImplementation
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
        bind<SuperContestEffectController>() with provider {
            SuperContestEffectControllerImplementation(
                instance(),
                instance()
            )
        }

        bind<EncounterMethodsController>() with provider {
            EncounterMethodsControllerImplementation(
                instance(),
                instance()
            )
        }
        bind<EncounterConditionsController>() with provider {
            EncounterConditionsControllerImplementation(
                instance(),
                instance()
            )
        }
        bind<EncounterConditionValuesController>() with provider {
            EncounterConditionValuesControllerImplementation(
                instance(),
                instance()
            )
        }

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

        bind<GameGenerationsController>() with provider {
            GameGenerationsControllerImplementation(instance(), instance())
        }
        bind<GamePokedexesController>() with provider {
            GamePokedexesControllerImplementation(instance(), instance())
        }
        bind<GameVersionController>() with provider {
            GameVersionControllerImplementation(instance(), instance())
        }
        bind<GameVersionGroupController>() with provider {
            GameVersionGroupControllerImplementation(instance(), instance())
        }

        bind<ItemAttributesController>() with provider {
            ItemAttributesControllerImplementation(instance(), instance())
        }
        bind<ItemCategoriesController>() with provider {
            ItemCategoriesControllerImplementation(instance(), instance())
        }
        bind<ItemController>() with provider {
            ItemControllerImplementation(instance(), instance())
        }
        bind<ItemFilingEffectsController>() with provider {
            ItemFilingEffectsControllerImplementation(instance(), instance())
        }
        bind<ItemPocketsController>() with provider {
            ItemPocketsControllerImplementation(instance(), instance())
        }

        bind<LocationAreasController>() with provider {
            LocationAreasControllerImplementation(instance(), instance())
        }
        bind<LocationController>() with provider {
            LocationControllerImplementation(instance(), instance())
        }
        bind<PalPakAreasController>() with provider {
            PalPakAreasControllerImplementation(instance(), instance())
        }
        bind<RegionsController>() with provider {
            RegionsControllerImplementation(instance(), instance())
        }

        bind<MachineController>() with provider { MachinesControllerImplementation(instance(), instance()) }

        bind<MoveAilmentsController>() with provider {
            MoveAilmentsControllerImplementation(instance(), instance())
        }
        bind<MoveBattleStylesController>() with provider {
            MoveBattleStylesControllerImplementation(instance(), instance())
        }
        bind<MoveCategoriesController>() with provider {
            MoveCategoriesControllerImplementation(instance(), instance())
        }
        bind<MoveController>() with provider {
            MoveControllerImplementation(instance(), instance())
        }
        bind<MoveDamageClassesController>() with provider {
            MoveDamageClassesControllerImplementation(instance(), instance())
        }
        bind<MoveLearnMethodsController>() with provider {
            MoveLearnMethodsControllerImplementation(instance(), instance())
        }
        bind<MoveTargetsController>() with provider {
            MoveTargetsControllerImplementation(instance(), instance())
        }

        bind() from singleton { DefaultController(instance()) }
    }

    private val featuresModule = Kodein.Module("FEATURES") {
        bind() from provider { BerriesController(instance(), instance(), instance()) }
        bind() from provider { ContestsController(instance(), instance(), instance()) }
        bind() from provider { EncountersController(instance(), instance(), instance()) }
        bind() from provider { EvolutionsController(instance(), instance()) }
        bind() from provider { GamesController(instance(), instance(), instance(), instance()) }
        bind() from provider { ItemsController(instance(), instance(), instance(), instance(), instance()) }
        bind() from provider { LocationsController(instance(), instance(), instance(), instance()) }
        bind() from provider { MachinesController(instance()) }
        bind() from provider { MovesController(instance(), instance(), instance(), instance(), instance(), instance(), instance()) }
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