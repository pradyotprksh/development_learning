package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Region(

    @SerialName("id") var id: Int? = null,
    @SerialName("locations") var locations: List<NameUrl> = emptyList(),
    @SerialName("main_generation") var mainGeneration: NameUrl? = NameUrl(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokedexes") var pokedexes: List<NameUrl> = emptyList(),
    @SerialName("version_groups") var versionGroups: List<NameUrl> = emptyList()

)
