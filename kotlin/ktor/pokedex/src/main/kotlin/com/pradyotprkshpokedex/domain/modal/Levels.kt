package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Levels(

    @SerialName("experience") val experience: Int? = null,
    @SerialName("level") val level: Int? = null

)