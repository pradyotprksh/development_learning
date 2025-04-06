package com.pradyotprakash.unitconverter.core.models.response

import com.pradyotprakash.unitconverter.utils.ConverterResponseStatus
import kotlinx.serialization.Serializable

@Serializable
data class ConverterResponse<T>(
    val status: ConverterResponseStatus,
    val code: String?,
    val message: String?,
    val data: T?,
)