package com.project.pradyotprakash.rental.data.repositories

import com.google.firebase.appcheck.FirebaseAppCheck
import com.orhanobut.logger.Logger
import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.services.AppCheckService
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*

class AppCheckRepository(
    private val firebaseAppCheck: FirebaseAppCheck,
    private val crashlyticsService: CrashlyticsService,
) : AppCheckService {
    override suspend fun getAppCheckToken() = flow {
        try {
            emit(RenterResponse.Loading)
            val appToken = firebaseAppCheck.getAppCheckToken(false).await()
            emit(RenterResponse.Success(appToken.token))
        } catch (e: Exception) {
            Logger.e(e.toString())
            crashlyticsService.submitCaughtException(e)
//            emit(RenterResponse.Error(RenterException(message = e.localizedMessage ?: "")))
            emit(RenterResponse.Success(UUID.randomUUID().toString()))
        }
    }
}