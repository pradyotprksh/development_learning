package com.pradyotprakash.pitgull.core.response

import android.util.Log
import com.pradyotprakash.pitgull.app.localizations.TR
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
            PitGullResponse.Success(responseBody)
        } else {
            errorBody()?.let { error ->
                val errorDetails = error.string()
                val jsonError = JSONObject(errorDetails)

                PitGullResponse.Error(
                    RenterException(
                        code = code(),
                        message = jsonError.getString("message")
                    )
                )
            } ?: kotlin.run {
                PitGullResponse.Error(
                    RenterException(
                        message = TR.noDataFoundError
                    )
                )
            }
        }
    } catch (e: Exception) {
        Log.e(TR.errorTag, e.toString())
        PitGullResponse.Error(
            RenterException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }