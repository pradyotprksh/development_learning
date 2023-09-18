package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.ContestEffect
import com.pradyotprkshpokedex.domain.modal.ContestType
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.SuperContestEffect

interface ContestService {
    suspend fun getTypeByPagination(offset: Int, limit: Int): Pagination
    suspend fun getTypeDetails(id: Int, path: String? = null): ContestType
    suspend fun getEffectByPagination(offset: Int, limit: Int): Pagination
    suspend fun getEffectDetails(id: Int, path: String? = null): ContestEffect
    suspend fun getSuperContestEffectByPagination(offset: Int, limit: Int): Pagination
    suspend fun getSuperContestEffectDetails(id: Int, path: String? = null): SuperContestEffect
}