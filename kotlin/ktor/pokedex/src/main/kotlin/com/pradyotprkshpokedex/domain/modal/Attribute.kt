package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attribute(

    @SerialName("descriptions") var descriptions: List<Descriptions> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("items") var items: List<NameUrl> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList()

)
