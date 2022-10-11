package com.project.pradyotprakash.demo.data.response

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
            DemoResponse.Success(responseBody)
        } else {
            errorBody()?.let { error ->
                val errorDetails = error.string()
                val jsonError = JSONObject(errorDetails)

                DemoResponse.Error(
                    RenterException(
                        code = jsonError.getInt("code"),
                        message = jsonError.getString("message")
                    )
                )
            } ?: kotlin.run {
                DemoResponse.Error(
                    RenterException(
                        message = "No Data Found"
                    )
                )
            }
        }
    } catch (e: Exception) {
        DemoResponse.Error(
            RenterException(
                message = e.localizedMessage ?: "No Data Found"
            )
        )
    }