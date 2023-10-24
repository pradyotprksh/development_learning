package com.pradyotprakash.libraryowner.core.response

import com.orhanobut.logger.Logger
import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.data.services.crashlytics.CrashlyticsService
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.parseResponse(crashlyticsService: CrashlyticsService) =
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
                    crashlyticsService.submitCaughtException(e)
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
        crashlyticsService.submitCaughtException(e)
        Logger.e(e.toString())
        OwnerResponse.Error(
            OwnerException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }