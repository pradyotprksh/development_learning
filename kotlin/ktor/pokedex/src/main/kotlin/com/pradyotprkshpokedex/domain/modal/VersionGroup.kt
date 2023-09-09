package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(

    @SerialName("generation") var generation: Generation? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("move_learn_methods") var moveLearnMethods: List<NameUrl> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("pokedexes") var pokedexes: List<Pokedexes> = emptyList(),
    @SerialName("regions") var regions: List<NameUrl> = emptyList(),
    @SerialName("versions") var versions: List<Versions> = emptyList()

)

