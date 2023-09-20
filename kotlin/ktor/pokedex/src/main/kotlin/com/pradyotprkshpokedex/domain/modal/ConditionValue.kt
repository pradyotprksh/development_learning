package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionValue(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("condition") val condition: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList()

)