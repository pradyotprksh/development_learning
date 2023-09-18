package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Levels(

    @SerialName("experience") var experience: Int? = null,
    @SerialName("level") var level: Int? = null

)