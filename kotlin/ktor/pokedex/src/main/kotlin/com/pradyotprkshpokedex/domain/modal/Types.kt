package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Types(

    @SerialName("slot") val slot: Int? = null,
    @SerialName("type") val type: NameUrl? = NameUrl()

)