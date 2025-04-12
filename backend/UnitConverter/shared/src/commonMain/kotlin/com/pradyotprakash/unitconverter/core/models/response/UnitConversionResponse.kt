package com.pradyotprakash.unitconverter.core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class UnitConversionResponse(
    val value: Double,
    val humanReadable: String,
)
