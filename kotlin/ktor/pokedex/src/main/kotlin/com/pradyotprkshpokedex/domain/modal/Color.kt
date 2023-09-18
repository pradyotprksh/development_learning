package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Color(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokemon_species") var pokemonSpecies: List<NameUrl> = emptyList()

)
