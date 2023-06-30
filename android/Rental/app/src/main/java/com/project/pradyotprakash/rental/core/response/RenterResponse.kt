package com.project.pradyotprakash.rental.core.response

/**
 * A common wrapper for the response
 */
sealed class RenterResponse<R> {
    /**
     * The success response type
     *
     * @param data Data got when got the success result
     * */
    data class Success<T>(val data: T) : RenterResponse<T>()

    /**
     * The error response type
     *
     * @param exception Exception details
     * */
    data class Error(val exception: RenterException) : RenterResponse<Nothing>()

    /**
     * The loading type, when the request is initiated
     * */
    object Loading : RenterResponse<Nothing>()

    /**
     * The Idle type, when the request is done
     * */
    object Idle : RenterResponse<Nothing>()
}

/**
 * An error/exception for the Renter application
 */
data class RenterException(
    val code: Int? = null,
    override val message: String = "",
) : Exception(message) {
    fun isNotFound() = code == 404

    fun isUnauthorizedRequest() = code == 401

    fun isInvalidRequest() = code == 409
}