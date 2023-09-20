package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Generation(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("abilities") val abilities: List<String> = emptyList(),
    @SerialName("main_region") val mainRegion: NameUrl? = NameUrl(),
    @SerialName("moves") val moves: List<NameUrl> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokemon_species") val pokemonSpecies: List<NameUrl> = emptyList(),
    @SerialName("types") val types: List<NameUrl> = emptyList(),
    @SerialName("version_groups") val versionGroups: List<NameUrl> = emptyList()

)