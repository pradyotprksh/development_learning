package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationI(

    @SerialName("red-blue") val redBlue: RedBlue? = RedBlue(),
    @SerialName("yellow") val yellow: Yellow? = Yellow()

)