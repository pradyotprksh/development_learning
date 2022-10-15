package com.project.pradyotprakash.rental.core.utils

import com.project.pradyotprakash.rental.BuildConfig

/**
 * A constant class for core level
 */
object Constants {
    private const val INFORMATION_URL = "renter/information"
    const val BASE_URL = BuildConfig.baseUrl
    const val informationURL = "$BASE_URL$INFORMATION_URL"
}