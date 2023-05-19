package com.pradyotprakash.findingfalcone.core.response

import com.orhanobut.logger.Logger
import com.pradyotprakash.findingfalcone.app.localization.TR
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
            FindingFalconeResponse.Success(responseBody)
        } else {
            errorBody()?.let { error ->
                val errorDetails = error.string()
                val jsonError = JSONObject(errorDetails)

                FindingFalconeResponse.Error(
                    FindingFalconeException(
                        code = jsonError.getInt("code"),
                        message = jsonError.getString("message")
                    )
                )
            } ?: kotlin.run {
                FindingFalconeResponse.Error(
                    FindingFalconeException(
                        message = TR.noDataFoundError
                    )
                )
            }
        }
    } catch (e: Exception) {
        Logger.e(e.toString())
        FindingFalconeResponse.Error(
            FindingFalconeException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }