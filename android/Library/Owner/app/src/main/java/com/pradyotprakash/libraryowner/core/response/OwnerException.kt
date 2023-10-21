package com.pradyotprakash.libraryowner.core.response

data class OwnerException(
    val code: Int? = null,
    override val message: String = "",
) : Exception(message)