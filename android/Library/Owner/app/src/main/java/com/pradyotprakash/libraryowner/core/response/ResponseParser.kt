package com.pradyotprakash.libraryowner.core.response

import com.orhanobut.logger.Logger
import com.pradyotprakash.libraryowner.app.localization.TR
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.parseResponse() =
    try {
        val responseBody = body()
        if (isSuccessful && responseBody != null) {
            OwnerResponse.Success(responseBody)
        } else {
            errorBody()?.let { error ->
                val errorDetails = error.string()
                val jsonError = JSONObject(errorDetails)

                // Get unsplash errors
                val jsonArray = try {
                    jsonError.getJSONArray("errors")
                } catch (e: Exception) {
                    Logger.e(e.toString())
                    JSONArray()
                }

                if (jsonArray.length() > 0) {
                    val errorMessage = jsonArray.join(", ")

                    OwnerResponse.Error(
                        OwnerException(
                            code = code(),
                            message = errorMessage
                        )
                    )
                } else {
                    OwnerResponse.Error(
                        OwnerException(
                            message = TR.noDataFoundError
                        )
                    )
                }
            } ?: kotlin.run {
                OwnerResponse.Error(
                    OwnerException(
                        message = TR.noDataFoundError
                    )
                )
            }
        }
    } catch (e: Exception) {
        Logger.e(e.toString())
        OwnerResponse.Error(
            OwnerException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }