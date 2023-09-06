package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvolutionChain(

    @SerialName("baby_trigger_item") var babyTriggerItem: String? = null,
    @SerialName("chain") var chain: Chain? = null,
    @SerialName("id") var id: Int? = null

)

