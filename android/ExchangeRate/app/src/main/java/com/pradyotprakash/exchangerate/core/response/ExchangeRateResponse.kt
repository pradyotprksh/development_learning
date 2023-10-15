package com.pradyotprakash.exchangerate.core.response

sealed class ExchangeRateResponse<R> {
    data class Success<T>(val data: T) : ExchangeRateResponse<T>()

    data class Error(val exception: ExchangeRateException) : ExchangeRateResponse<Nothing>()

    object Loading : ExchangeRateResponse<Nothing>()

    object Idle : ExchangeRateResponse<Nothing>()
}
