package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(

    @SerialName("ailment") val ailment: NameUrl? = NameUrl(),
    @SerialName("category") val category: NameUrl? = NameUrl(),
    @SerialName("min_hits") val minHits: String? = null,
    @SerialName("max_hits") val maxHits: String? = null,
    @SerialName("min_turns") val minTurns: String? = null,
    @SerialName("max_turns") val maxTurns: String? = null,
    @SerialName("drain") val drain: Int? = null,
    @SerialName("healing") val healing: Int? = null,
    @SerialName("crit_rate") val critRate: Int? = null,
    @SerialName("ailment_chance") val ailmentChance: Int? = null,
    @SerialName("flinch_chance") val flinchChance: Int? = null,
    @SerialName("stat_chance") val statChance: Int? = null

)