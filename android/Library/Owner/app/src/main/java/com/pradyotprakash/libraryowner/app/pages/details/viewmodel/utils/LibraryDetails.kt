package com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils

data class LibraryDetails(
    val name: String = "",
    val emailId: String = "",
    val emailIdSameAsCustomer: Boolean = false,
    val phoneNumber: String = "",
    val phoneNumberSameAsCustomer: Boolean = false,
    val address: String = ""
) {
    fun copyWith(
        name: String? = null,
        emailId: String? = null,
        phoneNumber: String? = null,
        address: String? = null,
        emailIdSameAsCustomer: Boolean? = null,
        phoneNumberSameAsCustomer: Boolean? = null,
    ) = LibraryDetails(
        name = name ?: this.name,
        emailId = emailId ?: this.emailId,
        phoneNumber = phoneNumber ?: this.phoneNumber,
        emailIdSameAsCustomer = emailIdSameAsCustomer ?: this.emailIdSameAsCustomer,
        phoneNumberSameAsCustomer = phoneNumberSameAsCustomer ?: this.phoneNumberSameAsCustomer,
        address = address ?: this.address
    )
}
