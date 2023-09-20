package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("generation") val generation: NameUrl? = NameUrl(),
    @SerialName("move_learn_methods") val moveLearnMethods: List<NameUrl> = emptyList(),
    @SerialName("pokedexes") val pokedexes: List<NameUrl> = emptyList(),
    @SerialName("regions") val regions: List<NameUrl> = emptyList(),
    @SerialName("versions") val versions: List<NameUrl> = emptyList()

)