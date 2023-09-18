package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AffectingMoves(

    @SerialName("increase") var increase: List<Increase> = emptyList(),
    @SerialName("decrease") var decrease: List<Decrease> = emptyList()

)