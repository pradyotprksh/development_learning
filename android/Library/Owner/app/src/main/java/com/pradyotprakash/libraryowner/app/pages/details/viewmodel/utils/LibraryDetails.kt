package com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils

import android.webkit.URLUtil
import com.pradyotprakash.libraryowner.app.utils.isValidEmailId
import com.pradyotprakash.libraryowner.app.utils.isValidName
import com.pradyotprakash.libraryowner.app.utils.isValidPhoneNumber

data class LibraryDetails(
    val name: String = "",
    val emailId: String = "",
    val emailIdSameAsCustomer: Boolean = false,
    val phoneNumber: String = "",
    val phoneNumberSameAsCustomer: Boolean = false,
    val address: String = "",
    val nameError: Boolean = false,
    val emailIdError: Boolean = false,
    val phoneNumberError: Boolean = false,
    val addressError: Boolean = false,
) {
    fun isValid(region: String) = name.isValidName() &&
            !(emailIdSameAsCustomer || emailId.isValidEmailId()) &&
            (phoneNumberSameAsCustomer || phoneNumber.isValidPhoneNumber(region)) &&
            (address.isNotBlank() && URLUtil.isValidUrl(address))

    fun checkValidity(region: String) = this.copy(
        nameError = !name.isValidName(),
        emailIdError = !(emailIdSameAsCustomer || emailId.isValidEmailId()),
        phoneNumberError = !(phoneNumberSameAsCustomer || phoneNumber.isValidPhoneNumber(region)),
        addressError = !(address.isNotBlank() && URLUtil.isValidUrl(address)),
    )
}
