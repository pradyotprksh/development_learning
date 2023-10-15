package com.pradyotprakash.exchangerate.core.response

import com.orhanobut.logger.Logger
import com.pradyotprakash.exchangerate.app.localization.TR
import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.parseResponse() =
    try {
        val responseBody = body()
        if (isSuccessful && responseBody != null) {
            ExchangeRateResponse.Success(responseBody)
        } else {
            errorBody()?.let { error ->
                val errorDetails = error.string()
                val jsonError = JSONObject(errorDetails).run {
                    getJSONObject("error")
                }

                ExchangeRateResponse.Error(
                    ExchangeRateException(
                        code = jsonError.getInt("code"),
                        message = jsonError.getString("info")
                    )
                )
            } ?: kotlin.run {
                ExchangeRateResponse.Error(
                    ExchangeRateException(
                        message = TR.noDataFoundError
                    )
                )
            }
        }
    } catch (e: Exception) {
        Logger.e(e.toString())
        ExchangeRateResponse.Error(
            ExchangeRateException(
                message = e.localizedMessage ?: TR.noDataFoundError
            )
        )
    }