package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AffectingNatures(

    @SerialName("decrease") val decrease: List<Decrease> = emptyList(),
    @SerialName("increase") val increase: List<Increase> = emptyList()

)