package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Varieties(

    @SerialName("is_default") val isDefault: Boolean? = null,
    @SerialName("pokemon") val pokemon: NameUrl? = NameUrl()

)