package com.pradyotprakash.libraryowner.app.pages.details.viewmodel

data class LibraryDetails(
    val name: String,
    val emailId: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val images: List<String> = emptyList(),
)
