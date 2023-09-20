package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DamageClass(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("descriptions") val descriptions: List<Descriptions> = emptyList(),
    @SerialName("moves") val moves: List<NameUrl> = emptyList()

)