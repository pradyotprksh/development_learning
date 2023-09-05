package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Firmness(
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null
)