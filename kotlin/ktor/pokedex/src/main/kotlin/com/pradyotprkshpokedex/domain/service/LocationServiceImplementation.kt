package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.domain.modal.Area
import com.pradyotprkshpokedex.domain.modal.Location
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.PalPakArea
import com.pradyotprkshpokedex.domain.modal.Region
import com.pradyotprkshpokedex.utils.Paths

class LocationServiceImplementation(private val networkClient: NetworkClient) : LocationService {
    override suspend fun getLocationDetails(id: Int, path: String?): Location {
        val location = networkClient.get<Location>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Locations.LOCATION}/${id}",
                fullPath = path
            )
        )

        return location.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getLocationByPagination(offset: Int, limit: Int): Pagination {
        val locations = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Locations.LOCATION,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return locations.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAreaDetails(id: Int, path: String?): Area {
        val locationArea = networkClient.get<Area>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Locations.LOCATION}-${Paths.Locations.AREA}/${id}",
                fullPath = path
            )
        )

        return locationArea.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getAreaByPagination(offset: Int, limit: Int): Pagination {
        val locationAreas = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Locations.LOCATION}-${Paths.Locations.AREA}",
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return locationAreas.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPalPakAreaDetails(id: Int, path: String?): PalPakArea {
        val palPakArea = networkClient.get<PalPakArea>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Locations.PAL_PAK_AREA}/${id}",
                fullPath = path
            )
        )

        return palPakArea.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getPalPakAreaByPagination(offset: Int, limit: Int): Pagination {
        val palPakAreas = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Locations.PAL_PAK_AREA,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return palPakAreas.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getRegionEffectDetails(id: Int, path: String?): Region {
        val region = networkClient.get<Region>(
            details = PokeApiRequestDetails(
                endpoint = "${Paths.Locations.REGION}/${id}",
                fullPath = path
            )
        )

        return region.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }

    override suspend fun getRegionEffectByPagination(offset: Int, limit: Int): Pagination {
        val regions = networkClient.get<Pagination>(
            details = PokeApiRequestDetails(
                endpoint = Paths.Locations.REGION,
                queries = mapOf(
                    Paths.Parameters.OFFSET to offset,
                    Paths.Parameters.LIMIT to limit
                )
            )
        )

        return regions.getOrElse { exception ->
            throw PokeApiException(error = exception.message ?: "Error while connecting to PokeApi. Please try again.")
        }
    }
}