package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RubySapphire(

    @SerialName("back_default") val backDefault: String? = null,
    @SerialName("back_shiny") val backShiny: String? = null,
    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("front_shiny") val frontShiny: String? = null

)