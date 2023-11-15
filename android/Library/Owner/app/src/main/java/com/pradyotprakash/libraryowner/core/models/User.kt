package com.pradyotprakash.libraryowner.core.models

data class User(
    val userId: String,
    val name: String,
    val emailId: String,
    val phoneNumber: String,
    val profileImage: String,
    val isAllDetailsAdded: Boolean,
    val userCreatedOn: Long,
    val detailsUpdatedOn: Long,
    val isUserALibrarian: Boolean,
    val isUserACustomer: Boolean,
)
