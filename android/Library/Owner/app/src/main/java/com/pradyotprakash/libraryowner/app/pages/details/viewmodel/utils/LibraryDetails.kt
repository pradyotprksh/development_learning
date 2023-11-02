package com.pradyotprakash.libraryowner.app.pages.details.viewmodel.utils

data class LibraryDetails(
    val name: String = "",
    val emailId: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val images: List<String> = emptyList(),
) {
    fun copyWith(
        name: String? = null,
        emailId: String? = null,
        phoneNumber: String? = null,
        address: String? = null,
        images: List<String>? = null,
    ) = LibraryDetails(
        name = name ?: this.name,
        emailId = emailId ?: this.emailId,
        phoneNumber = phoneNumber ?: this.phoneNumber,
        address = address ?: this.address,
        images = images ?: this.images,
    )
}
