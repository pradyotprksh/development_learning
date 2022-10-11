package com.project.pradyotprakash.rental.data.repositories

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.project.pradyotprakash.rental.core.services.CrashlyticsService

class CrashlyticsRepository(
    private val crashlytics: FirebaseCrashlytics,
): CrashlyticsService {
    override fun setUserId(userId: String) {
        crashlytics.setUserId(userId)
    }

    override fun removeUserId() {
        crashlytics.setUserId("")
    }

    override fun submitCaughtException(e: Exception) {
        crashlytics.recordException(e)
    }

    override fun submitAPICCaughtException(functionName: String?, e: Exception) {
        crashlytics.setCustomKey("functionName", functionName ?: "UnknownFunctionName")
        crashlytics.recordException(e)
    }
}