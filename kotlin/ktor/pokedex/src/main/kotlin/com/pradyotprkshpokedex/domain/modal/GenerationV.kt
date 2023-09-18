package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationV(

    @SerialName("black-white") var blackWhite: BlackWhite? = BlackWhite()

)