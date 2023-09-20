package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Region(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("locations") val locations: List<NameUrl> = emptyList(),
    @SerialName("main_generation") val mainGeneration: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokedexes") val pokedexes: List<NameUrl> = emptyList(),
    @SerialName("version_groups") val versionGroups: List<NameUrl> = emptyList()

)