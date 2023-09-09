package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(

    @SerialName("descriptions") var descriptions: List<Descriptions> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("is_main_series") var isMainSeries: Boolean? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokemon_entries") var pokemonEntries: List<PokemonEntries> = emptyList(),
    @SerialName("region") var region: String? = null,
    @SerialName("version_groups") var versionGroups: List<String> = emptyList()

)

