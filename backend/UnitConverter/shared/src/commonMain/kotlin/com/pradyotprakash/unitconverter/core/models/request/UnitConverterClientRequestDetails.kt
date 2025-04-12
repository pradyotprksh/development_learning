package com.pradyotprakash.unitconverter.core.models.request

data class UnitConverterClientRequestDetails(
    val endpoint: String,
    val queries: Map<String, Any> = emptyMap(),
    val body: Any? = null,
)