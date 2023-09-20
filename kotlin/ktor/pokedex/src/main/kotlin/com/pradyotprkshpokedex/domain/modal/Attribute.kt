package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attribute(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("descriptions") val descriptions: List<Descriptions> = emptyList(),
    @SerialName("items") val items: List<NameUrl> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList()

)