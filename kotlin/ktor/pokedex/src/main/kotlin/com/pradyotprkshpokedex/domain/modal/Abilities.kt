package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Abilities(

    @SerialName("is_hidden") val isHidden: Boolean? = null,
    @SerialName("slot") val slot: Int? = null,
    @SerialName("ability") val ability: NameUrl? = NameUrl()

)