package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Increase(

    @SerialName("max_change") val maxChange: Int? = null,
    @SerialName("nature") val nature: Nature? = Nature(),
    @SerialName("change") val change: Int? = null,
    @SerialName("move") val move: NameUrl? = NameUrl()

)