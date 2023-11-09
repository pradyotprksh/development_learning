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
    fun copyWith(
        name: String? = null,
        emailId: String? = null,
        phoneNumber: String? = null,
        profileImage: String? = null,
        nameError: Boolean? = null,
        emailIdError: Boolean? = null,
        phoneNumberError: Boolean? = null,
    ) = CustomerDetails(
        name = name ?: this.name,
        emailId = emailId ?: this.emailId,
        phoneNumber = phoneNumber ?: this.phoneNumber,
        profileImage = profileImage ?: this.profileImage,
        nameError = nameError ?: this.nameError,
        emailIdError = emailIdError ?: this.emailIdError,
        phoneNumberError = phoneNumberError ?: this.phoneNumberError,
    )

    fun isValid(region: String) = name.isValidName() &&
            emailId.isValidEmailId() &&
            phoneNumber.isValidPhoneNumber(region)
}
