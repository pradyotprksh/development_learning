package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GrowthRate(

    @SerialName("descriptions") var descriptions: List<Descriptions> = emptyList(),
    @SerialName("formula") var formula: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("levels") var levels: List<Levels> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("pokemon_species") var pokemonSpecies: List<NameUrl> = emptyList()

)

