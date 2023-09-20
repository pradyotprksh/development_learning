package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Home(

    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("front_female") val frontFemale: String? = null,
    @SerialName("front_shiny") val frontShiny: String? = null,
    @SerialName("front_shiny_female") val frontShinyFemale: String? = null

)