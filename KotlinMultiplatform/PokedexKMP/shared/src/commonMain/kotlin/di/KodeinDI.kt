package di

import core.network.Api
import core.network.NetworkClient
import core.repository.PokemonRepository
import core.service.PokemonService
import data.pokemon.PokemonDataService
import domain.repository.pokemon.PokemonRepositoryImplementation
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object KodeinDI {
    private val appModule = DI.Module(name = "app") {}

    private val repository = DI.Module(name = "repository") {
        bindSingleton<PokemonRepository> { PokemonRepositoryImplementation(instance()) }
    }

    private val service = DI.Module(name = "service") {
        bindSingleton<PokemonService> { PokemonDataService(instance()) }
    }

    private val api = DI.Module(name = "api") {
        bindSingleton { Api.createHttpClient() }
        bindSingleton { NetworkClient(instance()) }
    }

    val di = DI { importAll(appModule, repository, service, api) }
}