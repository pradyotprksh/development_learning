package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncounterDetails (

    @SerialName("chance"           ) var chance          : Int?              = null,
    @SerialName("condition_values" ) var conditionValues : List<String> = emptyList(),
    @SerialName("max_level"        ) var maxLevel        : Int?              = null,
    @SerialName("method"           ) var method          : Method?           = Method(),
    @SerialName("min_level"        ) var minLevel        : Int?              = null

)