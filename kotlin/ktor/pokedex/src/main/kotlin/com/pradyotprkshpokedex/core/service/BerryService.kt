package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.*

interface BerryService {
    suspend fun getBerriesByPagination(offset: Int, limit: Int): Berries
    suspend fun getBerryDetails(id: Int, path: String? = null): Berry
    suspend fun getBerryFirmnessDetails(id: Int, path: String? = null): BerryFirmness
    suspend fun getBerriesFirmnessByPagination(offset: Int, limit: Int): BerryFirmnesses
    suspend fun getBerryFlavorDetails(id: Int, path: String? = null): BerryFlavor
    suspend fun getBerriesFlavorByPagination(offset: Int, limit: Int): BerryFlavors
}