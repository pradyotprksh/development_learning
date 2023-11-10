package com.pradyotprakash.libraryowner.app.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.orhanobut.logger.Logger

fun String.isValidName(): Boolean = this.isNotBlank()

fun String.isValidEmailId(): Boolean =
    this.isNotBlank() && this.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\$".toRegex())

fun String.isValidPhoneNumber(region: String): Boolean = try {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val phoneNumber: Phonenumber.PhoneNumber = phoneUtil.parse(this, region)
    phoneUtil.isValidNumber(phoneNumber)
} catch (e: Exception) {
    Logger.e(e.toString())
    false
}