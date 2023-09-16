package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(

    @SerialName("attributes") var attributes: List<NameUrl> = emptyList(),
    @SerialName("baby_trigger_for") var babyTriggerFor: String? = null,
    @SerialName("category") var category: NameUrl? = NameUrl(),
    @SerialName("cost") var cost: Int? = null,
    @SerialName("effect_entries") var effectEntries: List<EffectEntries> = emptyList(),
    @SerialName("flavor_text_entries") var flavorTextEntries: List<FlavorTextEntries> = emptyList(),
    @SerialName("fling_effect") var flingEffect: String? = null,
    @SerialName("fling_power") var flingPower: String? = null,
    @SerialName("game_indices") var gameIndices: List<GameIndices> = emptyList(),
    @SerialName("held_by_pokemon") var heldByPokemon: List<String> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("machines") var machines: List<String> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList(),
    @SerialName("sprites") var sprites: Sprites? = Sprites()

)

