package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEntries(

    @SerialName("entry_number") val entryNumber: Int? = null,
    @SerialName("pokemon_species") val pokemonSpecies: NameUrl? = NameUrl()

)