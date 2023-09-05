package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Berries
import com.pradyotprkshpokedex.domain.modal.Berry

interface BerryService {
    suspend fun getAllBerries(path: String?): Berries
    suspend fun getBerryDetails(id: Int, path: String? = null): Berry
}