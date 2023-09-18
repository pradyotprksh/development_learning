package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class Silver(

    @SerialName("back_default") var backDefault: String? = null,
    @SerialName("back_shiny") var backShiny: String? = null,
    @SerialName("front_default") var frontDefault: String? = null,
    @SerialName("front_shiny") var frontShiny: String? = null

)