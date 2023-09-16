package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Area
import com.pradyotprkshpokedex.domain.modal.Areas
import com.pradyotprkshpokedex.domain.modal.Location
import com.pradyotprkshpokedex.domain.modal.Locations
import com.pradyotprkshpokedex.domain.modal.PalPakArea
import com.pradyotprkshpokedex.domain.modal.PalPakAreas
import com.pradyotprkshpokedex.domain.modal.Region
import com.pradyotprkshpokedex.domain.modal.Regions

interface LocationService {
    suspend fun getLocationDetails(id: Int, path: String? = null): Location
    suspend fun getLocationByPagination(offset: Int, limit: Int): Locations
    suspend fun getAreaDetails(id: Int, path: String? = null): Area
    suspend fun getAreaByPagination(offset: Int, limit: Int): Areas
    suspend fun getPalPakAreaDetails(id: Int, path: String? = null): PalPakArea
    suspend fun getPalPakAreaByPagination(offset: Int, limit: Int): PalPakAreas
    suspend fun getRegionEffectDetails(id: Int, path: String? = null): Region
    suspend fun getRegionEffectByPagination(offset: Int, limit: Int): Regions
}