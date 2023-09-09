package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flavors(
    @SerialName("flavor") val flavor: NameUrl? = NameUrl(),
    @SerialName("potency") val potency: Int? = null
)