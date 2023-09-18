package com.pradyotprkshpokedex.domain.service

import com.pradyotprkshpokedex.core.network.NetworkClient
import com.pradyotprkshpokedex.core.service.EncounterService
import com.pradyotprkshpokedex.domain.modal.Condition
import com.pradyotprkshpokedex.domain.modal.ConditionValue
import com.pradyotprkshpokedex.domain.modal.Method
import com.pradyotprkshpokedex.domain.modal.Pagination

class EncounterServiceImplementation(private val networkClient: NetworkClient) : EncounterService {
    override suspend fun getMethodByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getMethodDetails(id: Int, path: String?): Method {
        TODO("Not yet implemented")
    }

    override suspend fun getConditionByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getConditionDetails(id: Int, path: String?): Condition {
        TODO("Not yet implemented")
    }

    override suspend fun getConditionValueByPagination(offset: Int, limit: Int): Pagination {
        TODO("Not yet implemented")
    }

    override suspend fun getConditionValueDetails(id: Int, path: String?): ConditionValue {
        TODO("Not yet implemented")
    }
}