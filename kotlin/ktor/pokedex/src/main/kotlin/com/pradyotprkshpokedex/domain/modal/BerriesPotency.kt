package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerriesPotency(

    @SerialName("berry") val berry: NameUrl? = NameUrl(),
    @SerialName("potency") val potency: Int? = null

)