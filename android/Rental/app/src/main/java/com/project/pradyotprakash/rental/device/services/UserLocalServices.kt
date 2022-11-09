package com.project.pradyotprakash.rental.device.services

import com.project.pradyotprakash.rental.app.utils.UserType

interface UserLocalServices {
    fun saveSelectedUserType(userType: String)
    val userType: UserType
    fun saveCurrentLocation(latitude: String, longitude: String)
    val currentLocation: Pair<String, String>
}