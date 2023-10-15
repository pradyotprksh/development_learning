package com.pradyotprakash.exchangerate.core.utils

object Utils {
    fun calculateMinutesBetweenTimestamps(timestamp1: Long, timestamp2: Long): Long {
        val millisecondsDifference = timestamp1 - timestamp2
        return millisecondsDifference / (1000 * 60)
    }

    fun getCurrentTimestamp() = System.currentTimeMillis()
}