package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Generation(

    @SerialName("abilities") var abilities: List<String> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("main_region") var mainRegion: NameUrl? = NameUrl(),
    @SerialName("moves") var moves: List<NameUrl> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokemon_species") var pokemonSpecies: List<NameUrl> = emptyList(),
    @SerialName("types") var types: List<NameUrl> = emptyList(),
    @SerialName("version_groups") var versionGroups: List<NameUrl> = emptyList()

)

