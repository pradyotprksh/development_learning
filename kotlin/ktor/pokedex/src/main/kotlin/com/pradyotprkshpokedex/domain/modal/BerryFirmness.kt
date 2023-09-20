package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerryFirmness(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("berries") val berries: List<NameUrl> = emptyList(),
    @SerialName("names") val names: List<Names> = emptyList()

)
