package com.project.pradyotprakash.rental.core.services

import com.project.pradyotprakash.rental.core.response.RenterResponse
import kotlinx.coroutines.flow.Flow

interface AppCheckService {
    suspend fun getAppCheckToken() : Flow<RenterResponse<String>>
}