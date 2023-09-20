package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexNumbers(

    @SerialName("entry_number") val entryNumber: Int? = null,
    @SerialName("pokedex") val pokedex: Pokedex? = Pokedex()

)