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
}