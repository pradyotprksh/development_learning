package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEntries(

    @SerialName("entry_number") var entryNumber: Int? = null,
    @SerialName("pokemon_species") var pokemonSpecies: NameUrl? = NameUrl()

)