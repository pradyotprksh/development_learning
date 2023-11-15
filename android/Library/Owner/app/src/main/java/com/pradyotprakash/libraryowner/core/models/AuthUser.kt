package com.pradyotprakash.libraryowner.core.models

data class AuthUser(
    val username: String,
    val emailId: String,
    val phoneNumber: String,
    val userId: String,
    val createdOnTimestamp: Long?,
)