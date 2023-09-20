package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("base_experience") val baseExperience: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("is_default") val isDefault: Boolean? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("weight") val weight: Int? = null,
    @SerialName("abilities") val abilities: List<Abilities> = emptyList(),
    @SerialName("forms") val forms: List<NameUrl> = emptyList(),
    @SerialName("game_indices") val gameIndices: List<GameIndices> = emptyList(),
    @SerialName("held_items") val heldItems: List<HeldItems> = emptyList(),
    @SerialName("location_area_encounters") val locationAreaEncounters: String? = null,
    @SerialName("moves") val moves: List<Moves> = emptyList(),
    @SerialName("species") val species: NameUrl? = NameUrl(),
    @SerialName("sprites") val sprites: Sprites? = Sprites(),
    @SerialName("stats") val stats: List<Stats> = emptyList(),
    @SerialName("types") val types: List<Types> = emptyList(),
    @SerialName("past_types") val pastTypes: List<PastTypes> = emptyList()

)

