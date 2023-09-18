package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Area
import com.pradyotprkshpokedex.domain.modal.Location
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.PalPakArea
import com.pradyotprkshpokedex.domain.modal.Region

interface LocationService {
    suspend fun getLocationDetails(id: Int, path: String? = null): Location
    suspend fun getLocationByPagination(offset: Int, limit: Int): Pagination
    suspend fun getAreaDetails(id: Int, path: String? = null): Area
    suspend fun getAreaByPagination(offset: Int, limit: Int): Pagination
    suspend fun getPalPakAreaDetails(id: Int, path: String? = null): PalPakArea
    suspend fun getPalPakAreaByPagination(offset: Int, limit: Int): Pagination
    suspend fun getRegionEffectDetails(id: Int, path: String? = null): Region
    suspend fun getRegionEffectByPagination(offset: Int, limit: Int): Pagination
}