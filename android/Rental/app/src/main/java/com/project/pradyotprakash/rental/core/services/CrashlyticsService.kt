package com.project.pradyotprakash.rental.core.services

interface CrashlyticsService {
    fun setUserId(userId: String)
    fun removeUserId()
    fun submitCaughtException(e: Exception)
    fun submitAPICCaughtException(functionName: String?, e: Exception)
}