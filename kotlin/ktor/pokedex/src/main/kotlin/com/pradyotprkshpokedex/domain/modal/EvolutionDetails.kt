package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvolutionDetails(

    @SerialName("gender") var gender: String? = null,
    @SerialName("held_item") var heldItem: String? = null,
    @SerialName("item") var item: String? = null,
    @SerialName("known_move") var knownMove: String? = null,
    @SerialName("known_move_type") var knownMoveType: String? = null,
    @SerialName("location") var location: String? = null,
    @SerialName("min_affection") var minAffection: String? = null,
    @SerialName("min_beauty") var minBeauty: String? = null,
    @SerialName("min_happiness") var minHappiness: String? = null,
    @SerialName("min_level") var minLevel: Int? = null,
    @SerialName("needs_overworld_rain") var needsOverworldRain: Boolean? = null,
    @SerialName("party_species") var partySpecies: String? = null,
    @SerialName("party_type") var partyType: String? = null,
    @SerialName("relative_physical_stats") var relativePhysicalStats: String? = null,
    @SerialName("time_of_day") var timeOfDay: String? = null,
    @SerialName("trade_species") var tradeSpecies: String? = null,
    @SerialName("trigger") var trigger: NameUrl? = null,
    @SerialName("turn_upside_down") var turnUpsideDown: Boolean? = null

)