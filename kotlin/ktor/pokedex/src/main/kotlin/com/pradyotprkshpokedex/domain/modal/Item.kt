package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("cost") val cost: Int? = null,
    @SerialName("fling_power") val flingPower: Int? = null,
    @SerialName("fling_effect") val flingEffect: NameUrl? = NameUrl(),
    @SerialName("attributes") val attributes: List<NameUrl> = emptyList(),
    @SerialName("category") val category: NameUrl? = NameUrl(),
    @SerialName("effect_entries") val effectEntries: List<NameUrl> = emptyList(),
    @SerialName("flavor_text_entries") val flavorTextEntries: List<NameUrl> = emptyList(),
    @SerialName("game_indices") val gameIndices: List<GameIndices> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList(),
    @SerialName("sprites") val sprites: Sprites? = Sprites(),
    @SerialName("held_by_pokemon") val heldByPokemon: List<HeldByPokemon> = emptyList(),
    @SerialName("baby_trigger_for") val babyTriggerFor: BabyTriggerFor? = BabyTriggerFor()

)

