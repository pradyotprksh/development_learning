package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GrowthRate(

    @SerialName("descriptions") val descriptions: List<Descriptions> = emptyList(),
    @SerialName("formula") val formula: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("levels") val levels: List<Levels> = emptyList(),
    @SerialName("name") val name: String? = null,
    @SerialName("pokemon_species") val pokemonSpecies: List<NameUrl> = emptyList()

)

