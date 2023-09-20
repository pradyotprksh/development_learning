package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AffectingMoves(

    @SerialName("increase") val increase: List<Increase> = emptyList(),
    @SerialName("decrease") val decrease: List<Decrease> = emptyList()

)