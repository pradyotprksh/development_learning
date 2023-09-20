package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DreamWorld(

    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("front_female") val frontFemale: String? = null

)