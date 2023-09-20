package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerryFlavor(

    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("berries") val berries: List<Berries> = emptyList(),
    @SerialName("contest_type") val contestType: NameUrl? = NameUrl(),
    @SerialName("names") val names: List<Names> = emptyList()

)

