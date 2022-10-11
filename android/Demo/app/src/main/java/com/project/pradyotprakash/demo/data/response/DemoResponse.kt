package com.project.pradyotprakash.demo.data.response

/**
 * A common wrapper for the response
 */
sealed class DemoResponse<out R> {
    /**
     * The success response type
     *
     * @param data Data got when got the success result
     * */
    data class Success<out T>(val data: T) : DemoResponse<T>()

    /**
     * The error response type
     *
     * @param exception Exception details
     * */
    data class Error(val exception: RenterException) : DemoResponse<Nothing>()

    /**
     * The loading type, when the request is initiated
     * */
    object Loading : DemoResponse<Nothing>()

    /**
     * The Idle type, when the request is done
     * */
    object Idle : DemoResponse<Nothing>()
}

/**
 * An error/exception for the Renter application
 */
class RenterException(
    val code: Int? = null,
    message: String = "",
) : Exception(message)