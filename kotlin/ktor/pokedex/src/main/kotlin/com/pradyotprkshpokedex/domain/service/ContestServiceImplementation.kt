package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.ContestService
import com.pradyotprkshpokedex.domain.modal.ContestEffect
import com.pradyotprkshpokedex.domain.modal.ContestType
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.SuperContestEffect
import com.pradyotprkshpokedex.utils.Paths

class ContestServiceImplementation(private val networkClient: NetworkClient) : ContestService {
    override suspend fun getTypeByPagination(offset: Int, limit: Int): Pagination {
        val types = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Contests.CONTESTS}-${Paths.Contests.TYPE}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return types.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getTypeDetails(id: Int, path: String?): ContestType {
        val typeDetails = networkClient.get<ContestType>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Contests.CONTESTS}-${Paths.Contests.TYPE}/$id",
                fullPath = path,
            )
        )

        return typeDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEffectByPagination(offset: Int, limit: Int): Pagination {
        val effects = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Contests.CONTESTS}-${Paths.Contests.EFFECT}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return effects.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getEffectDetails(id: Int, path: String?): ContestEffect {
        val effectDetails = networkClient.get<ContestEffect>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Contests.CONTESTS}-${Paths.Contests.EFFECT}",
                fullPath = path,
            )
        )

        return effectDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getSuperContestEffectByPagination(offset: Int, limit: Int): Pagination {
        val superContestEffects = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Contests.SUPER_CONTEST_EFFECT,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return superContestEffects.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getSuperContestEffectDetails(id: Int, path: String?): SuperContestEffect {
        val superContestEffect = networkClient.get<SuperContestEffect>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Contests.SUPER_CONTEST_EFFECT,
                fullPath = path,
            )
        )

        return superContestEffect.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}