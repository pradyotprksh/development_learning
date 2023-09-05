package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Firmness(
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null
)