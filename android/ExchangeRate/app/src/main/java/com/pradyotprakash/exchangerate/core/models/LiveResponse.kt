package com.pradyotprakash.exchangerate.core.models

data class LiveResponse(
    val success: Boolean? = null,
    val timestamp: Int? = null,
    val source: String? = null,
    val quotes: Map<String, Double>? = emptyMap()
)