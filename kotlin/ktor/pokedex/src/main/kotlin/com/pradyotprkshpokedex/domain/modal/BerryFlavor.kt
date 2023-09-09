package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerryFlavor(

    @SerialName("berries") val berries: List<BerriesPotency> = emptyList(),
    @SerialName("contest_type") val contestType: NameUrl? = NameUrl(),
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("names") val names: List<Names> = emptyList()

)

