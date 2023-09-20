package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEncounters(

    @SerialName("pokemon") val pokemon: NameUrl? = NameUrl(),
    @SerialName("version_details") val versionDetails: List<VersionDetails> = emptyList(),
    @SerialName("base_score") val baseScore: Int? = null,
    @SerialName("rate") val rate: Int? = null,
    @SerialName("pokemon_species") val pokemonSpecies: NameUrl? = NameUrl()

)