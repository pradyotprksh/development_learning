package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(

    @SerialName("effect_changes") val effectChanges: List<EffectChanges> = emptyList(),
    @SerialName("effect_entries") val effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("generation") val generation: Generation? = Generation(),
    @SerialName("id") val id: Int? = null,
    @SerialName("is_main_series") val isMainSeries: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("pokemon") val pokemon: List<PokemonDetails> = emptyList()

)

