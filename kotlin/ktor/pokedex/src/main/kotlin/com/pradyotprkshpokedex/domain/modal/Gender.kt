package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gender(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("pokemon_species_details") var pokemonSpeciesDetails: List<PokemonSpeciesDetails> = emptyList(),
    @SerialName("required_for_evolution") var requiredForEvolution: List<NameUrl> = emptyList()

)

