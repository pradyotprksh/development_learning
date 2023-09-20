package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionDetails(

    @SerialName("rarity") val rarity: Int? = null,
    @SerialName("version") val version: NameUrl? = NameUrl(),
    @SerialName("rate") val rate: Int? = null,
    @SerialName("max_chance") val maxChance: Int? = null,
    @SerialName("encounter_details") val encounterDetails: List<EncounterDetails> = emptyList()

)