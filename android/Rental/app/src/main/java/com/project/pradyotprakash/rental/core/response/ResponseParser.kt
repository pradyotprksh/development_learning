package com.project.pradyotprakash.rental.core.response

import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import retrofit2.Response

/**
 * A response parser extension for the response got from the
 * retrofit
 */
fun <T> Response<T>.parseResponse() =
    try {
        val responseBody = body()
        if (isSuccessful && responseBody != null) {
            RenterResponse.Success(responseBody)
        } else {
            RenterResponse.Error(
                RenterException(
                    code = (responseBody as? DefaultEntity<*>)?.code,
                    message = (responseBody as? DefaultEntity<*>)?.message ?: TR.noDataFoundError
                )
            )
        }
    } catch (e: Exception) {
        RenterResponse.Error(
            RenterException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }