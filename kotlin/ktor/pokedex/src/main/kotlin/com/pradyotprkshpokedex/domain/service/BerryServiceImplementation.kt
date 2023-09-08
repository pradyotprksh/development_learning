package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.Berries
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.domain.modal.BerryFirmness
import com.pradyotprkshpokedex.domain.modal.BerryFirmnesses
import com.pradyotprkshpokedex.domain.modal.BerryFlavor
import com.pradyotprkshpokedex.domain.modal.BerryFlavors
import com.pradyotprkshpokedex.utils.Paths

class BerryServiceImplementation(private val networkClient: NetworkClient) : BerryService {
    override suspend fun getBerriesByPagination(offset: Int, limit: Int): Berries {
        val berries = networkClient.get<Berries>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Berries.BERRY,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return berries.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBerryDetails(id: Int, path: String?): Berry {
        val berryDetails = networkClient.get<Berry>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Berries.BERRY}/$id",
                fullPath = path,
            )
        )

        return berryDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBerryFirmnessDetails(id: Int, path: String?): BerryFirmness {
        val berryFirmnessDetails = networkClient.get<BerryFirmness>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Berries.BERRY}-${Paths.Berries.FIRMNESS}/$id",
                fullPath = path,
            )
        )

        return berryFirmnessDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBerriesFirmnessByPagination(offset: Int, limit: Int): BerryFirmnesses {
        val berryFirmnesses = networkClient.get<BerryFirmnesses>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Berries.BERRY}-${Paths.Berries.FIRMNESS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return berryFirmnesses.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBerryFlavorDetails(id: Int, path: String?): BerryFlavor {
        val berryFlavorDetails = networkClient.get<BerryFlavor>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Berries.BERRY}-${Paths.Berries.FLAVOR}/$id",
                fullPath = path,
            )
        )

        return berryFlavorDetails.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getBerriesFlavorByPagination(offset: Int, limit: Int): BerryFlavors {
        val berryFlavors = networkClient.get<BerryFlavors>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Berries.BERRY}-${Paths.Berries.FIRMNESS}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return berryFlavors.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}