package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ailment(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("moves") val moves: List<NameUrl> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList()

)