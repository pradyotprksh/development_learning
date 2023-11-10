package com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils

import com.pradyotprakash.libraryowner.app.utils.isValidEmailId
import com.pradyotprakash.libraryowner.app.utils.isValidName
import com.pradyotprakash.libraryowner.app.utils.isValidPhoneNumber

data class CustomerDetails(
    val name: String = "",
    val emailId: String = "",
    val phoneNumber: String = "",
    val profileImage: String = "",
    val nameError: Boolean = false,
    val emailIdError: Boolean = false,
    val phoneNumberError: Boolean = false,
) {
    fun checkValidity(region: String) = this.copy(
        nameError = !name.isValidName(),
        emailIdError = !emailId.isValidEmailId(),
        phoneNumberError = !name.isValidPhoneNumber(region),
    )
}
