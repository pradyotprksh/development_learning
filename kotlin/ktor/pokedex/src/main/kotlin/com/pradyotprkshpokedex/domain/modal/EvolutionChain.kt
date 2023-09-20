package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvolutionChain(

    @SerialName("id") val id: Int? = null,
    @SerialName("baby_trigger_item") val babyTriggerItem: String? = null,
    @SerialName("chain") val chain: Chain? = Chain()

)

