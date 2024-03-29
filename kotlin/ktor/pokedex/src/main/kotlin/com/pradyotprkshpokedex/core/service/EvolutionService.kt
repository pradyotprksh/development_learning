package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.EvolutionChain
import com.pradyotprkshpokedex.domain.modal.EvolutionTrigger
import com.pradyotprkshpokedex.domain.modal.Pagination

interface EvolutionService {
    suspend fun getEvolutionChainDetails(id: Int, path: String? = null): EvolutionChain
    suspend fun getEvolutionChainByPagination(offset: Int, limit: Int): Pagination
    suspend fun getEvolutionTriggerDetails(id: Int, path: String? = null): EvolutionTrigger
    suspend fun getEvolutionTriggerByPagination(offset: Int, limit: Int): Pagination
}