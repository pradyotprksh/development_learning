package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperContestEffects(

    @SerialName("count") var count: Int? = null,
    @SerialName("next") var next: String? = null,
    @SerialName("previous") var previous: String? = null,
    @SerialName("results") var results: List<NameUrl> = emptyList()

)