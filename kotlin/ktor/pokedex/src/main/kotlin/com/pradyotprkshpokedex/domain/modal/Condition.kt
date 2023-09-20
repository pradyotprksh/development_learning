package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Condition(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("values") val values: List<NameUrl> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList()

)
