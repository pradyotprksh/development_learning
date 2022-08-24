package com.project.pradyotprakash.rental.core.response

import com.project.pradyotprakash.rental.app.localization.TR
import org.json.JSONObject
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
            errorBody()?.let { error ->
                val errorDetails = error.string()
                val jsonError = JSONObject(errorDetails)

                RenterResponse.Error(
                    RenterException(
                        code = jsonError.getInt("code"),
                        message = jsonError.getString("message")
                    )
                )
            } ?: kotlin.run {
                RenterResponse.Error(
                    RenterException(
                        message = TR.noDataFoundError
                    )
                )
            }
        }
    } catch (e: Exception) {
        RenterResponse.Error(
            RenterException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }