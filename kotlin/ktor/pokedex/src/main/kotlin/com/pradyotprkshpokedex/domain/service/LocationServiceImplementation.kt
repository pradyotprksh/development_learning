package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.domain.modal.Area
import com.pradyotprkshpokedex.domain.modal.Location
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.PalPakArea
import com.pradyotprkshpokedex.domain.modal.Region

class LocationServiceImplementation(private val networkClient: NetworkClient) : LocationService {
    override suspend fun getLocationDetails(id: Int, path: String?): Location {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getAreaDetails(id: Int, path: String?): Area {
        TODO("Not yet implemented")
    }

    override suspend fun getAreaByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getPalPakAreaDetails(id: Int, path: String?): PalPakArea {
        TODO("Not yet implemented")
    }

    override suspend fun getPalPakAreaByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getRegionEffectDetails(id: Int, path: String?): Region {
        TODO("Not yet implemented")
    }

    override suspend fun getRegionEffectByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }
}