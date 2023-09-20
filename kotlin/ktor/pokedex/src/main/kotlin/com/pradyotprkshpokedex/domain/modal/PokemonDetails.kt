package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(

    @SerialName("is_hidden") val isHidden: Boolean? = null,
    @SerialName("pokemon") val pokemon: NameUrl? = NameUrl(),
    @SerialName("slot") val slot: Int? = null

)