package com.pradyotprakash.libraryowner.data.services.crashlytics

interface CrashlyticsService {
    fun setUserId(userId: String)
    fun removeUserId()
    fun submitCaughtException(e: Exception)
    fun submitAPICCaughtException(functionName: String?, e: Exception)
}