package com.pradyotprakash.exchangerate.core.response

data class ExchangeRateException(
    val code: Int? = null,
    override val message: String = "",
) : Exception(message)