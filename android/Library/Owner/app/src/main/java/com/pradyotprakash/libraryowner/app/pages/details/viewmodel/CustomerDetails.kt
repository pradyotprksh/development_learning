package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

data class CustomerDetails(
    val name: String = "",
    val emailId: String = "",
    val phoneNumber: String = "",
    val nameError: Boolean = false,
    val emailIdError: Boolean = false,
    val phoneNumberError: Boolean = false,
) {
    fun copyWith(
        name: String? = null,
        emailId: String? = null,
        phoneNumber: String? = null,
        nameError: Boolean? = null,
        emailIdError: Boolean? = null,
        phoneNumberError: Boolean? = null,
    ) = CustomerDetails(
        name = name ?: this.name,
        emailId = emailId ?: this.emailId,
        phoneNumber = phoneNumber ?: this.phoneNumber,
        nameError = nameError ?: this.nameError,
        emailIdError = emailIdError ?: this.emailIdError,
        phoneNumberError = phoneNumberError ?: this.phoneNumberError,
    )
}
