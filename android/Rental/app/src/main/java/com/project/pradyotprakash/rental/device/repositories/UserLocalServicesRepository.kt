package com.project.pradyotprakash.rental.device.repositories

import android.content.SharedPreferences
import com.project.pradyotprakash.rental.app.utils.UserType
import com.project.pradyotprakash.rental.device.services.UserLocalServices
import com.project.pradyotprakash.rental.device.utils.LocalStorageConstants

class UserLocalServicesRepository(
    private val sharedPreference: SharedPreferences
) : UserLocalServices {
    override fun saveSelectedUserType(userType: String) {
        with(sharedPreference.edit()) {
            putString(LocalStorageConstants.userType, userType)
            apply()
        }
    }

    override val userType: UserType
        get() = UserType.values().find {
            it.name == sharedPreference.getString(
                LocalStorageConstants.userType,
                UserType.Unknown.name
            )
        } ?: UserType.Unknown

    override fun saveCurrentLocation(latitude: String, longitude: String) {
        with(sharedPreference.edit()) {
            putString(LocalStorageConstants.latitude, latitude)
            putString(LocalStorageConstants.longitude, longitude)
            apply()
        }
    }

    override val currentLocation: Pair<String, String>
        get() {
            val latitude = sharedPreference.getString(LocalStorageConstants.latitude, "") ?: ""
            val longitude = sharedPreference.getString(LocalStorageConstants.longitude, "") ?: ""
            return Pair(latitude, longitude)
        }
}