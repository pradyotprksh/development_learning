package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Berries
import com.pradyotprkshpokedex.domain.modal.Berry

interface BerryService {
    suspend fun getBerriesByPagination(offset: Int, limit: Int): Berries
    suspend fun getBerryDetails(id: Int, path: String? = null): Berry
}