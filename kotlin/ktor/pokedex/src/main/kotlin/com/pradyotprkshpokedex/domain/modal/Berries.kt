package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Berries(

    @SerialName("potency") val potency: Int? = null,
    @SerialName("berry") val berry: NameUrl? = NameUrl()

)