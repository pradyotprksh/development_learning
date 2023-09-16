package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEncounters (

    @SerialName("base_score"      ) var baseScore      : Int?            = null,
    @SerialName("pokemon_species" ) var pokemonSpecies : NameUrl? = NameUrl(),
    @SerialName("rate"            ) var rate           : Int?            = null

)