package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName

data class ListResponse<T>(
    @SerialName("count") val count: Int,
    @SerialName("results") val data: T,
)
