package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.ContestService
import com.pradyotprkshpokedex.domain.modal.ContestEffect
import com.pradyotprkshpokedex.domain.modal.ContestType
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.modal.SuperContestEffect

class ContestServiceImplementation(private val networkClient: NetworkClient) : ContestService {
    override suspend fun getTypeByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getTypeDetails(id: Int, path: String?): ContestType {
        TODO("Not yet implemented")
    }

    override suspend fun getEffectByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getEffectDetails(id: Int, path: String?): ContestEffect {
        TODO("Not yet implemented")
    }

    override suspend fun getSuperContestEffectByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getSuperContestEffectDetails(id: Int, path: String?): SuperContestEffect {
        TODO("Not yet implemented")
    }
}