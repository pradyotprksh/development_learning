package com.pradyotprakash.xfullstack.data.response

import core.utils.ResponseStatus
import kotlinx.serialization.Serializable

@Serializable
data class XFullStackResponse<T>(
    val status: ResponseStatus,
    val errorCode: String?,
    val data: T?,
)