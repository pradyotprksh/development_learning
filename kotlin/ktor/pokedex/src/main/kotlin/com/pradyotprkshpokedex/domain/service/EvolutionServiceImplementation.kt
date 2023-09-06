package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.EvolutionService
import com.pradyotprkshpokedex.domain.modal.EvolutionChain
import com.pradyotprkshpokedex.domain.modal.EvolutionChains
import com.pradyotprkshpokedex.domain.modal.EvolutionTrigger
import com.pradyotprkshpokedex.domain.modal.EvolutionTriggers
import com.pradyotprkshpokedex.utils.Paths

class EvolutionServiceImplementation(
    private val networkClient: NetworkClient
) : EvolutionService {
    override suspend fun getEvolutionChainDetails(id: Int, path: String?): EvolutionChain {
        val evolutionChainDetails = networkClient.get<EvolutionChain>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Evolution.EVOLUTION}-${Paths.Evolution.CHAINS}/$id",
                fullPath = path,
            )
        )

        return evolutionChainDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEvolutionChainByPagination(offset: Int, limit: Int): EvolutionChains {
        val evolutionChains = networkClient.get<EvolutionChains>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Evolution.EVOLUTION}-${Paths.Evolution.CHAINS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return evolutionChains.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEvolutionTriggerDetails(id: Int, path: String?): EvolutionTrigger {
        val evolutionTriggerDetails = networkClient.get<EvolutionTrigger>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Evolution.EVOLUTION}-${Paths.Evolution.TRIGGERS}/$id",
                fullPath = path,
            )
        )

        return evolutionTriggerDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEvolutionTriggerByPagination(offset: Int, limit: Int): EvolutionTriggers {
        val evolutionTriggers = networkClient.get<EvolutionTriggers>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Evolution.EVOLUTION}-${Paths.Evolution.TRIGGERS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return evolutionTriggers.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}