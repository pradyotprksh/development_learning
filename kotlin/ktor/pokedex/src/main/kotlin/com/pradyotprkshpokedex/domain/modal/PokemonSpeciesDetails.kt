package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesDetails(

    @SerialName("pokemon_species") val pokemonSpecies: NameUrl? = NameUrl(),
    @SerialName("rate") val rate: Int? = null

)