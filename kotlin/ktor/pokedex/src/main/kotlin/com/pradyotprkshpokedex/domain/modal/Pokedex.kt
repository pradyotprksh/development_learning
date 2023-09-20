package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("is_main_series") val isMainSeries: Boolean? = null,
    @SerialName("descriptions") val descriptions: List<Descriptions> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokemon_entries") val pokemonEntries: List<PokemonEntries> = emptyList(),
    @SerialName("region") val region: NameUrl? = NameUrl(),
    @SerialName("version_groups") val versionGroups: List<NameUrl> = emptyList()

)
