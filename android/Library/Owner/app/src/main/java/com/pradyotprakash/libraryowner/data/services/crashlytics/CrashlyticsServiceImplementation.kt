package com.pradyotprakash.libraryowner.data.services.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsServiceImplementation(
    private val crashlytics: FirebaseCrashlytics,
) : CrashlyticsService {
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