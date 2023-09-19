package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.domain.modal.Generation
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.Pokedex
import com.pradyotprkshpokedex.domain.modal.Version
import com.pradyotprkshpokedex.domain.modal.VersionGroup
import com.pradyotprkshpokedex.utils.Paths

class GameServiceImplementation(private val networkClient: NetworkClient) : GameService {
    override suspend fun getGenerationDetails(id: Int, path: String?): Generation {
        val generation = networkClient.get<Generation>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Games.GENERATION}/$id",
                fullPath = path
            )
        )

        return generation.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getGenerationByPagination(offset: Int, limit: Int): Pagination {
        val generations = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Games.GENERATION,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return generations.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPokedexDetails(id: Int, path: String?): Pokedex {
        val pokedex = networkClient.get<Pokedex>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Games.POKEDEX}/$id",
                fullPath = path
            )
        )

        return pokedex.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPokedexByPagination(offset: Int, limit: Int): Pagination {
        val pokedexes = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Games.POKEDEX,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return pokedexes.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getVersionDetails(id: Int, path: String?): Version {
        val version = networkClient.get<Version>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Games.VERSION}/$id",
                fullPath = path
            )
        )

        return version.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getVersionByPagination(offset: Int, limit: Int): Pagination {
        val versions = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Games.VERSION,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return versions.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getVersionGroupDetails(id: Int, path: String?): VersionGroup {
        val versionGroup = networkClient.get<VersionGroup>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Games.VERSION_GROUP}/$id",
                fullPath = path
            )
        )

        return versionGroup.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getVersionGroupByPagination(offset: Int, limit: Int): Pagination {
        val versionGroups = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Games.VERSION_GROUP,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return versionGroups.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}