package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexNumbers(

    @SerialName("entry_number") var entryNumber: Int? = null,
    @SerialName("pokedex") var pokedex: Pokedex? = Pokedex()

)