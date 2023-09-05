package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerriesPotency (

    @SerialName("berry"   ) var berry   : Results? = null,
    @SerialName("potency" ) var potency : Int?   = null

)