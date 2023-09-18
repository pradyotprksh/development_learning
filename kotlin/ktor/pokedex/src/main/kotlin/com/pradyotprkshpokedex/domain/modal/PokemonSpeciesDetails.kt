package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesDetails(

    @SerialName("pokemon_species") var pokemonSpecies: NameUrl? = NameUrl(),
    @SerialName("rate") var rate: Int? = null

)