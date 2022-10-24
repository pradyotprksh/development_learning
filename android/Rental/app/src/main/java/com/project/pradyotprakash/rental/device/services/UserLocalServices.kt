package com.project.pradyotprakash.rental.device.services

import com.project.pradyotprakash.rental.app.utils.UserType

interface UserLocalServices {
    fun saveSelectedUserType(userType: String)
    val userType: UserType
}