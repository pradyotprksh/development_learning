package com.pradyotprakash.libraryowner.app.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

fun String.isValidName(): Boolean = this.isNotBlank()

fun String.isValidEmailId(): Boolean =
    this.isNotBlank() && this.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\$".toRegex())

fun String.isValidPhoneNumber(region: String): Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val phoneNumber: Phonenumber.PhoneNumber = phoneUtil.parse(this, region)
    return phoneUtil.isValidNumber(phoneNumber)
}