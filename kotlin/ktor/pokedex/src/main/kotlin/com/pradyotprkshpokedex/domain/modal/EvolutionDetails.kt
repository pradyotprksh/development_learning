package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvolutionDetails(

    @SerialName("item") val item: String? = null,
    @SerialName("trigger") val trigger: NameUrl? = NameUrl(),
    @SerialName("gender") val gender: String? = null,
    @SerialName("held_item") val heldItem: String? = null,
    @SerialName("known_move") val knownMove: String? = null,
    @SerialName("known_move_type") val knownMoveType: String? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("min_level") val minLevel: Int? = null,
    @SerialName("min_happiness") val minHappiness: String? = null,
    @SerialName("min_beauty") val minBeauty: String? = null,
    @SerialName("min_affection") val minAffection: String? = null,
    @SerialName("needs_overworld_rain") val needsOverworldRain: Boolean? = null,
    @SerialName("party_species") val partySpecies: String? = null,
    @SerialName("party_type") val partyType: String? = null,
    @SerialName("relative_physical_stats") val relativePhysicalStats: String? = null,
    @SerialName("time_of_day") val timeOfDay: String? = null,
    @SerialName("trade_species") val tradeSpecies: String? = null,
    @SerialName("turn_upside_down") val turnUpsideDown: Boolean? = null

)