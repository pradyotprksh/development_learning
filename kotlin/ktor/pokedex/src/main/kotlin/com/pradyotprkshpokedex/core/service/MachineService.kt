package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Machine
import com.pradyotprkshpokedex.domain.modal.Pagination

interface MachineService {
    suspend fun getMachinesByPagination(offset: Int, limit: Int): Pagination
    suspend fun getMachineDetails(id: Int, path: String? = null): Machine
}