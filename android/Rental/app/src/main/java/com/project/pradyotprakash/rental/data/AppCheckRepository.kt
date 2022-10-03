package com.project.pradyotprakash.rental.data

import com.google.firebase.appcheck.FirebaseAppCheck
import com.project.pradyotprakash.rental.core.response.RenterException
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.services.AppCheckService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AppCheckRepository(
    private val firebaseAppCheck: FirebaseAppCheck,
) : AppCheckService {
    override suspend fun getAppCheckToken() = flow {
        try {
            emit(RenterResponse.Loading)
            val appToken = firebaseAppCheck.getAppCheckToken(false).await()
            emit(RenterResponse.Success(appToken.token))
        } catch (e: Exception) {
            emit(RenterResponse.Error(RenterException(message = e.localizedMessage ?: "")))
        }
    }
}