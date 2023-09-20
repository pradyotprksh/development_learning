package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gender(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("pokemon_species_details") val pokemonSpeciesDetails: List<PokemonSpeciesDetails> = emptyList(),
    @SerialName("required_for_evolution") val requiredForEvolution: List<NameUrl> = emptyList()

)

