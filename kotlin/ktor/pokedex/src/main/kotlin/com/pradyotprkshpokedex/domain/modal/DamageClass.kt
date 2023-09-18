package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DamageClass(

    @SerialName("descriptions") var descriptions: List<Descriptions> = emptyList(),
    @SerialName("id") var id: Int? = null,
    @SerialName("moves") var moves: List<NameUrl> = emptyList(),
    @SerialName("name") var name: String? = null,
    @SerialName("names") var names: List<Names> = emptyList()

)