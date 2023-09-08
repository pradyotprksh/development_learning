package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Berries
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.domain.modal.BerryFirmness
import com.pradyotprkshpokedex.domain.modal.BerryFirmnesses
import com.pradyotprkshpokedex.domain.modal.BerryFlavor
import com.pradyotprkshpokedex.domain.modal.BerryFlavors

interface BerryService {
    suspend fun getBerriesByPagination(offset: Int, limit: Int): Berries
    suspend fun getBerryDetails(id: Int, path: String? = null): Berry
    suspend fun getBerryFirmnessDetails(id: Int, path: String? = null): BerryFirmness
    suspend fun getBerriesFirmnessByPagination(offset: Int, limit: Int): BerryFirmnesses
    suspend fun getBerryFlavorDetails(id: Int, path: String? = null): BerryFlavor
    suspend fun getBerriesFlavorByPagination(offset: Int, limit: Int): BerryFlavors
}