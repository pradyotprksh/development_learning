package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncounterDetails(

    @SerialName("min_level") val minLevel: Int? = null,
    @SerialName("max_level") val maxLevel: Int? = null,
    @SerialName("condition_values") val conditionValues: List<NameUrl> = emptyList(),
    @SerialName("chance") val chance: Int? = null,
    @SerialName("method") val method: Method? = Method()

)