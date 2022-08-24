package com.project.pradyotprakash.rental.core.utils

import com.project.pradyotprakash.rental.app.utils.UserType

/**
 * A constant class for core level
 */
object Constants {
    private const val INFORMATION_URL = "renter/information"
    const val BASE_URL = "http://192.168.1.35:5000"
    const val informationURL = "$BASE_URL$INFORMATION_URL"
    var currentUserType: UserType? = null
}