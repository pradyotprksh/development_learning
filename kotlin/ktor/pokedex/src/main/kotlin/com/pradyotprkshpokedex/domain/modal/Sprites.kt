package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(

    @SerialName("default") val default: String? = null,
    @SerialName("back_default") val backDefault: String? = null,
    @SerialName("back_female") val backFemale: String? = null,
    @SerialName("back_shiny") val backShiny: String? = null,
    @SerialName("back_shiny_female") val backShinyFemale: String? = null,
    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("front_female") val frontFemale: String? = null,
    @SerialName("front_shiny") val frontShiny: String? = null,
    @SerialName("front_shiny_female") val frontShinyFemale: String? = null,
    @SerialName("other") val other: Other? = Other(),
    @SerialName("versions") val versions: Versions? = Versions()

)