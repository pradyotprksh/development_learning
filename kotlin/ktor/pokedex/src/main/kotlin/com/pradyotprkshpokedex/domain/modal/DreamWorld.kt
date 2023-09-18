package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DreamWorld(

    @SerialName("front_default") var frontDefault: String? = null,
    @SerialName("front_female") var frontFemale: String? = null

)