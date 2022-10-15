package com.project.pradyotprakash.rental.device.services

interface UserLocalServices {
    fun saveSelectedUserType(userType: String)
    val userType: String
}