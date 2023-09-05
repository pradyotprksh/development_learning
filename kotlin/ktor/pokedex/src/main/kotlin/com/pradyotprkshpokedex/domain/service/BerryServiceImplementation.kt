package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.Berries
import com.pradyotprkshpokedex.domain.modal.Berry

class BerryServiceImplementation(private val networkClient: NetworkClient): BerryService {
    override suspend fun getAllBerries(path: String?): Berries {
        val berries = networkClient.get<Berries>(
            details = PokeApiRequestDetails(
                endpoint = "berry",
                fullPath = path,
            )
        )

        return berries.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBerryDetails(id: Int, path: String?): Berry {
        val berryDetails = networkClient.get<Berry>(
            details = PokeApiRequestDetails(
                endpoint = "berry/$id",
                fullPath = path,
            )
        )

        return berryDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}