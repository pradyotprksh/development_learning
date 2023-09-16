package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestCombos (

    @SerialName("normal" ) var normal : Normal? = Normal(),
    @SerialName("super"  ) var superKey  : Super?  = Super()

)