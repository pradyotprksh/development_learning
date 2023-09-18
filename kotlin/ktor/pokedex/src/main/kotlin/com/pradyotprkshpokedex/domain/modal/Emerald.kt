package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Emerald(

    @SerialName("front_default") var frontDefault: String? = null,
    @SerialName("front_shiny") var frontShiny: String? = null

)