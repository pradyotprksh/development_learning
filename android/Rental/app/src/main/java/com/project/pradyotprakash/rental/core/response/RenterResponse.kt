package com.project.pradyotprakash.rental.core.response

sealed class RenterResponse<out R> {
    data class Success<out T>(val data: T) : RenterResponse<T>()
    data class Error(val exception: RenterException) : RenterResponse<Nothing>()
    object Loading : RenterResponse<Nothing>()
}

class RenterException(
    val code: Int? = null,
    message: String = "",
) : Exception(message)