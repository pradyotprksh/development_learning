package com.pradyotprkshpokedex.core.service

import com.pradyotprkshpokedex.domain.modal.Condition
import com.pradyotprkshpokedex.domain.modal.ConditionValue
import com.pradyotprkshpokedex.domain.modal.ConditionValues
import com.pradyotprkshpokedex.domain.modal.Conditions
import com.pradyotprkshpokedex.domain.modal.Method
import com.pradyotprkshpokedex.domain.modal.Methods

interface EncounterService {
    suspend fun getMethodByPagination(offset: Int, limit: Int): Methods
    suspend fun getMethodDetails(id: Int, path: String? = null): Method
    suspend fun getConditionByPagination(offset: Int, limit: Int): Conditions
    suspend fun getConditionDetails(id: Int, path: String? = null): Condition
    suspend fun getConditionValueByPagination(offset: Int, limit: Int): ConditionValues
    suspend fun getConditionValueDetails(id: Int, path: String? = null): ConditionValue
}