package com.pradyotprakash.libraryowner.core.response

sealed class OwnerResponse<R> {
    data class Success<T>(val data: T) : OwnerResponse<T>()

    data class Error(val exception: OwnerException) : OwnerResponse<Nothing>()

    object Loading : OwnerResponse<Nothing>()

    object Idle : OwnerResponse<Nothing>()
}