package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionDetails(

    @SerialName("rate") var rate: Int? = null,
    @SerialName("version") var version: Version? = Version(),
    @SerialName("max_chance") var maxChance: Int? = null,
    @SerialName("encounter_details") var encounterDetails: List<EncounterDetails> = emptyList(),
    @SerialName("rarity") var rarity: Int? = null

)