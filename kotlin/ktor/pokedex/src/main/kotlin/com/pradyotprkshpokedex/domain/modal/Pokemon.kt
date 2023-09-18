package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("base_experience") var baseExperience: Int? = null,
    @SerialName("height") var height: Int? = null,
    @SerialName("is_default") var isDefault: Boolean? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("weight") var weight: Int? = null,
    @SerialName("abilities") var abilities: List<Abilities> = emptyList(),
    @SerialName("forms") var forms: List<NameUrl> = emptyList(),
    @SerialName("game_indices") var gameIndices: List<GameIndices> = emptyList(),
    @SerialName("held_items") var heldItems: List<HeldItems> = emptyList(),
    @SerialName("location_area_encounters") var locationAreaEncounters: String? = null,
    @SerialName("moves") var moves: List<Moves> = emptyList(),
    @SerialName("species") var species: NameUrl? = NameUrl(),
    @SerialName("sprites") var sprites: Sprites? = Sprites(),
    @SerialName("stats") var stats: List<Stats> = emptyList(),
    @SerialName("types") var types: List<Types> = emptyList(),
    @SerialName("past_types") var pastTypes: List<PastTypes> = emptyList()

)

