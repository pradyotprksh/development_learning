package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestCombos(

    @SerialName("normal") val normal: Normal? = Normal(),
    @SerialName("super") val superDetails: Super? = Super()

)