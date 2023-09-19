package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails

class DefaultServiceImplementation(val networkClient: NetworkClient) {
    suspend inline fun <reified T> makeGetRequest(path: String): T {
        val details = networkClient.get<T>(
            details = PokeApiRequestDetails(
                endpoint = "",
                fullPath = path,
            )
        )

        return details.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}