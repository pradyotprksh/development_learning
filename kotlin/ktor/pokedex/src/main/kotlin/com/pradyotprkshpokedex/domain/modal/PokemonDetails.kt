package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(

    @SerialName("is_hidden") var isHidden: Boolean? = null,
    @SerialName("pokemon") var pokemon: NameUrl? = NameUrl(),
    @SerialName("slot") var slot: Int? = null

)