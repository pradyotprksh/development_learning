package com.pradyotprakash.exchangerate.core.models

data class AllCurrenciesResponse(
    val success: Boolean? = null,
    val currencies: Map<String, String>? = emptyMap()
)
