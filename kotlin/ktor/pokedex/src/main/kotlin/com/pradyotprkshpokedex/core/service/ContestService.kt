package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.ContestEffect
import com.pradyotprkshpokedex.domain.modal.ContestEffects
import com.pradyotprkshpokedex.domain.modal.ContestTypeDetails
import com.pradyotprkshpokedex.domain.modal.ContestTypes
import com.pradyotprkshpokedex.domain.modal.SuperContestEffect
import com.pradyotprkshpokedex.domain.modal.SuperContestEffects

interface ContestService {
    suspend fun getTypeByPagination(offset: Int, limit: Int): ContestTypes
    suspend fun getTypeDetails(id: Int, path: String? = null): ContestTypeDetails
    suspend fun getEffectByPagination(offset: Int, limit: Int): ContestEffects
    suspend fun getEffectDetails(id: Int, path: String? = null): ContestEffect
    suspend fun getSuperContestEffectByPagination(offset: Int, limit: Int): SuperContestEffects
    suspend fun getSuperContestEffectDetails(id: Int, path: String? = null): SuperContestEffect
}