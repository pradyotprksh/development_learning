package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Types(

    @SerialName("slot") var slot: Int? = null,
    @SerialName("type") var type: NameUrl? = NameUrl()

)