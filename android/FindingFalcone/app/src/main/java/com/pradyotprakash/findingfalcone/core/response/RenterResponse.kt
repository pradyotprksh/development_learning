package com.pradyotprakash.findingfalcone.core.response

/**
 * A common wrapper for the response
 */
sealed class FindingFalconeResponse<out R> {
    /**
     * The success response type
     *
     * @param data Data got when got the success result
     * */
    data class Success<out T>(val data: T) : FindingFalconeResponse<T>()

    /**
     * The error response type
     *
     * @param exception Exception details
     * */
    data class Error(val exception: FindingFalconeException) : FindingFalconeResponse<Nothing>()

    /**
     * The loading type, when the request is initiated
     * */
    object Loading : FindingFalconeResponse<Nothing>()

    /**
     * The Idle type, when the request is done
     * */
    object Idle : FindingFalconeResponse<Nothing>()
}

/**
 * An error/exception for the FindingFalcone application
 */
data class FindingFalconeException(
    val code: Int? = null,
    override val message: String = "",
) : Exception(message) {
    fun isNotFound() = code == 404
}