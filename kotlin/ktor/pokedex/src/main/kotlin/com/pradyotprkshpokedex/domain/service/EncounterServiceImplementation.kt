package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.EncounterService
import com.pradyotprkshpokedex.domain.modal.Condition
import com.pradyotprkshpokedex.domain.modal.ConditionValue
import com.pradyotprkshpokedex.domain.modal.Method
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.utils.Paths

class EncounterServiceImplementation(private val networkClient: NetworkClient) : EncounterService {
    override suspend fun getMethodByPagination(offset: Int, limit: Int): Pagination {
        val methods = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Encounters.ENCOUNTERS}-${Paths.Encounters.METHODS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return methods.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getMethodDetails(id: Int, path: String?): Method {
        val method = networkClient.get<Method>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Encounters.ENCOUNTERS}-${Paths.Encounters.METHODS}/$id",
                fullPath = path,
            )
        )

        return method.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getConditionByPagination(offset: Int, limit: Int): Pagination {
        val conditions = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Encounters.ENCOUNTERS}-${Paths.Encounters.CONDITION}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return conditions.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getConditionDetails(id: Int, path: String?): Condition {
        val condition = networkClient.get<Condition>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Encounters.ENCOUNTERS}-${Paths.Encounters.CONDITION}/$id",
                fullPath = path,
            )
        )

        return condition.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getConditionValueByPagination(offset: Int, limit: Int): Pagination {
        val conditionValues = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Encounters.ENCOUNTERS}-${Paths.Encounters.CONDITION_VALUE}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return conditionValues.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getConditionValueDetails(id: Int, path: String?): ConditionValue {
        val conditionValue = networkClient.get<ConditionValue>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Encounters.ENCOUNTERS}-${Paths.Encounters.CONDITION_VALUE}/$id",
                fullPath = path,
            )
        )

        return conditionValue.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}