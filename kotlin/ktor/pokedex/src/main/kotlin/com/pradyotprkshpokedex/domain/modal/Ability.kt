package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(

    @SerialName("effect_changes") var effectChanges: List<EffectChanges> = emptyList(),
    @SerialName("effect_entries") var effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("flavor_text_entries") var flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("generation") var generation: Generation? = Generation(),
    @SerialName("id") var id: Int? = null,
    @SerialName("is_main_series") var isMainSeries: Boolean? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("pokemon") var pokemon: List<PokemonDetails> = emptyList()

)

