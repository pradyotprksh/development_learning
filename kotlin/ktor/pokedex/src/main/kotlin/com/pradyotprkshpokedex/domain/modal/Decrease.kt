package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Decrease(

    @SerialName("max_change") var maxChange: Int? = null,
    @SerialName("nature") var nature: Nature? = Nature(),
    @SerialName("change") var change: Int? = null,
    @SerialName("move") var move: NameUrl? = NameUrl()

)