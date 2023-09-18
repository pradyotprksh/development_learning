package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AffectingNatures(

    @SerialName("decrease") var decrease: List<Decrease> = emptyList(),
    @SerialName("increase") var increase: List<Increase> = emptyList()

)